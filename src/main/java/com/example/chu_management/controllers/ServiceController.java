package com.example.chu_management.controllers;

import com.example.chu_management.composite.ServiceComponent;
import com.example.chu_management.composite.ServiceGroupe;
import com.example.chu_management.composite.ServiceUnitaire;
import com.example.chu_management.entities.Batiment;
import com.example.chu_management.entities.Service;
import com.example.chu_management.mediator.MediatorConcret;
import com.example.chu_management.mediator.ServiceAdministratif;
import com.example.chu_management.mediator.ServiceMedical;
import com.example.chu_management.repositories.BatimentRepository;
import com.example.chu_management.repositories.ServiceRepository;
import com.example.chu_management.observer.SujetObservable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private BatimentRepository batimentRepository;

    @Autowired
    private SujetObservable serviceAdministration;

    // Afficher la liste des services
    @GetMapping
    public String listServices(Model model) {
        List<Service> services = serviceRepository.findAll();
        List<Batiment> batiments = batimentRepository.findAll();
        model.addAttribute("batiments", batiments); // Passer la liste des bâtiments au modèle
        // Création d'un groupe de services
        ServiceGroupe serviceGroupe = new ServiceGroupe();

        // Ajout des services à un groupe
        for (Service s : services) {
            ServiceComponent serviceUnitaire = new ServiceUnitaire(s);
            serviceGroupe.ajouterService(serviceUnitaire);
        }

        model.addAttribute("services", services);
        model.addAttribute("totalServices", services.size());
        model.addAttribute("serviceGroupe", serviceGroupe); // Groupe de services pour afficher dans la vue
        return "services";
    }

    // Ajouter un service
    @PostMapping("/add")
    public String addService(@ModelAttribute Service service, @RequestParam Long batiment) {
        Batiment selectedBatiment = batimentRepository.findById(batiment)
                                                      .orElseThrow(() -> new IllegalArgumentException("Bâtiment non trouvé"));
        service.setBatiment(selectedBatiment); // Associer le bâtiment au service
        serviceRepository.save(service); // Sauvegarder le service avec la relation au bâtiment
        return "redirect:/services"; // Redirection après ajout
    }
    
    // Modifier un service
    @GetMapping("/edit/{id}")
    public String editService(@PathVariable Long id, Model model) {
        List<Batiment> batiments = batimentRepository.findAll();
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service non trouvé"));
        model.addAttribute("batiments", batiments);
        model.addAttribute("service", service);
        return "edit-service";
    }

    // Mettre à jour un service
    @PostMapping("/update/{id}")
    public String updateService(@PathVariable Long id, @ModelAttribute Service service) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service non trouvé"));

        existingService.setNom(service.getNom());
        existingService.setDescription(service.getDescription());
        serviceRepository.save(existingService);

        // Notifier les observateurs après mise à jour
        serviceAdministration.notifierObservateurs("Le service a été mis à jour : " + existingService.getNom());

        return "redirect:/services";
    }

    // Supprimer un service
    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service non trouvé"));
        serviceRepository.delete(service);

        // Notifier les observateurs après suppression
        serviceAdministration.notifierObservateurs("Le service a été supprimé : " + service.getNom());

        return "redirect:/services"; // Retourner à la liste des services
    }

    @PostMapping("/add-mediator")
    public String addMediator() {

        MediatorConcret mediator = new MediatorConcret();

        // Récupérer les services déjà existants depuis la base de données
        Service serviceMedical = serviceRepository.findByNom("Service Médical");
        Service serviceAdministratif = serviceRepository.findByNom("Service Administratif");
    
        // Vérification que les services existent
        if (serviceMedical != null && serviceAdministratif != null) {
            // Créer des instances de ServiceMedical et ServiceAdministratif en utilisant leurs noms
            ServiceMedical sm = new ServiceMedical(serviceMedical.getNom());  // Utiliser le nom du service récupéré
            ServiceAdministratif sa = new ServiceAdministratif(serviceAdministratif.getNom());
    
            // Ajouter les services au médiateur (en tant que ServiceCollegue)
            mediator.ajouterCollegue(sm);  // sm est de type ServiceCollegue, donc cela fonctionne
            mediator.ajouterCollegue(sa);  // sa est aussi de type ServiceCollegue
    
            // Simuler l'envoi d'un message
            sm.envoyerMessage("Il y a un nouveau patient.");
            sa.recevoirMessage("Il y a un nouveau patient.");
        }
    
        return "redirect:/services";
    }
            
}
