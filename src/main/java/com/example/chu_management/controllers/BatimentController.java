package com.example.chu_management.controllers;

import com.example.chu_management.entities.Batiment;
import com.example.chu_management.factories.BatimentFactory;
import com.example.chu_management.adapter.AdaptateurBatiment;
import com.example.chu_management.repositories.BatimentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/batiments")
public class BatimentController {
    private final BatimentRepository batimentRepository;

    public BatimentController(BatimentRepository batimentRepository) {
        this.batimentRepository = batimentRepository;
    }

    @GetMapping
    public String listBatiments(Model model) {
        List<Batiment> batiments = batimentRepository.findAll();
        model.addAttribute("batiments", batiments);
        return "batiments";
    }

    @PostMapping("/add")
    public String addBatiment(@RequestParam String type,
            @RequestParam String emplacement,
            @RequestParam String taille,
            @RequestParam String fonctionnalite,
            @RequestParam String description) {
        Batiment batiment = BatimentFactory.createBatiment(type, emplacement, taille, fonctionnalite, description);
        batimentRepository.save(batiment);
        return "redirect:/batiments";
    }

    @GetMapping("/edit/{id}")
    public String editBatiment(@PathVariable Long id, Model model) {
        Batiment batiment = batimentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batiment non trouvé"));
        model.addAttribute("batiment", batiment);
        return "edit-batiment";
    }

    @PostMapping("/update/{id}")
    public String updateBatiment(@PathVariable Long id,
            @RequestParam String nom,
            @RequestParam String emplacement,
            @RequestParam String taille,
            @RequestParam String fonctionnalite,
            @RequestParam String description) {
        Batiment batiment = batimentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bâtiment non trouvé"));

        batiment.setNom(nom);
        batiment.setEmplacement(emplacement);
        batiment.setTaille(taille);
        batiment.setFonctionnalite(fonctionnalite);
        batiment.setDescription(description);

        batimentRepository.save(batiment);
        return "redirect:/batiments";
    }

    @PostMapping("/adapter/{id}")
    public String adapterBatiment(@PathVariable Long id, @RequestParam String nouvelleFonctionnalite) {
        Batiment batimentExistant = batimentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bâtiment introuvable avec l'ID : " + id));

        // Utilisation de l'adaptateur pour obtenir les nouvelles données
        AdaptateurBatiment adaptateur = new AdaptateurBatiment(batimentExistant, nouvelleFonctionnalite);

        // Modifier l'entité existante avec les nouvelles valeurs de l'adaptateur
        batimentExistant.setFonctionnalite(adaptateur.getFonctionnalite());

        // Enregistrer l'entité modifiée
        batimentRepository.save(batimentExistant);
        return "redirect:/batiments";
    }

    @PostMapping("/reset/{id}")
    public String resetBatiment(@PathVariable Long id) {
        Batiment batimentExistant = batimentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bâtiment introuvable avec l'ID : " + id));

        // Restaurer la fonctionnalité d'origine
        batimentExistant.setFonctionnalite(getFonctionnaliteOriginale(batimentExistant));

        // Sauvegarder les modifications
        batimentRepository.save(batimentExistant);

        return "redirect:/batiments";
    }

    private String getFonctionnaliteOriginale(Batiment batiment) {
        return switch (batiment.getNom().toLowerCase()) {
            case "administration" -> "Gestion";
            case "urgence" -> "Urgence";
            case "radiologie" -> "Imagerie";
            default -> "Fonctionnalité inconnue";
        };
    }

    @GetMapping("/delete/{id}")
    public String deleteBatiment(@PathVariable Long id) {
        batimentRepository.deleteById(id);
        return "redirect:/batiments";
    }
}
