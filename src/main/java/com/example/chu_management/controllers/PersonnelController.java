package com.example.chu_management.controllers;

import com.example.chu_management.entities.Personnel;
import com.example.chu_management.factories.PersonnelFactory;
import com.example.chu_management.factories.MedecinFactory;
import com.example.chu_management.factories.InfirmierFactory;
import com.example.chu_management.repositories.PersonnelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personnel")
public class PersonnelController {
    private final PersonnelRepository personnelRepository;

    public PersonnelController(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @GetMapping
    public String listPersonnel(@RequestParam(required = false) String search, Model model) {
        List<Personnel> personnelList;
        if (search != null && !search.isEmpty()) {
            personnelList = personnelRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrFonctionContainingIgnoreCase(
                    search, search, search);
        } else {
            personnelList = personnelRepository.findAll();
        }
        model.addAttribute("personnelList", personnelList);
        model.addAttribute("search", search);
        return "personnel";
    }

    @PostMapping("/add")
    public String addPersonnel(@RequestParam String type, 
                               @RequestParam String nom,
                               @RequestParam String prenom) {
        PersonnelFactory factory;
        if ("medecin".equalsIgnoreCase(type)) {
            factory = new MedecinFactory();
        } else if ("infirmier".equalsIgnoreCase(type)) {
            factory = new InfirmierFactory();
        } else {
            throw new IllegalArgumentException("Type de personnel inconnu : " + type);
        }

        Personnel personnel = factory.creerPersonnel(nom, prenom);
        personnelRepository.save(personnel);
        return "redirect:/personnel";
    }

    @GetMapping("/edit/{id}")
    public String editPersonnel(@PathVariable Long id, Model model) {
        Personnel personnel = personnelRepository.findById(id).orElseThrow(() -> new RuntimeException("Personnel non trouvé"));
        model.addAttribute("personnel", personnel);
        return "edit-personnel";
    }

    @PostMapping("/update")
    public String updatePersonnel(@RequestParam Long id,
                                @RequestParam String nom,
                                @RequestParam String prenom,
                                @RequestParam String fonction) {
        Personnel personnel = personnelRepository.findById(id).orElseThrow(() -> new RuntimeException("Personnel non trouvé"));
        personnel.setNom(nom);
        personnel.setPrenom(prenom);
        personnel.setFonction(fonction);
        personnelRepository.save(personnel);
        return "redirect:/personnel";
    }


    @GetMapping("/delete/{id}")
    public String deletePersonnel(@PathVariable Long id) {
        personnelRepository.deleteById(id);
        return "redirect:/personnel";
    }
}
