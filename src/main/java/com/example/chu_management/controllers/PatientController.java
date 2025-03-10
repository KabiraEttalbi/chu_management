package com.example.chu_management.controllers;

import com.example.chu_management.entities.Patient;
import com.example.chu_management.facade.PatientFacade;
import com.example.chu_management.observer.GestionPatients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientFacade patientFacade;
    private final GestionPatients gestionPatients;

    public PatientController(PatientFacade patientFacade, GestionPatients gestionPatients) {
        this.patientFacade = patientFacade;
        this.gestionPatients = gestionPatients;
    }

    @GetMapping
    public String listPatients(@RequestParam(required = false) String search, Model model) {
        List<Patient> patients;
        if (search != null && !search.isEmpty()) {
            patients = gestionPatients.getPatients(); // Utilisation de GestionPatients pour récupérer tous les patients
            patients.removeIf(p -> !(p.getNom().toLowerCase().contains(search.toLowerCase()) ||
                    p.getPrenom().toLowerCase().contains(search.toLowerCase()) ||
                    p.getCode().toLowerCase().contains(search.toLowerCase()) ||
                    p.getSituationMedicale().toLowerCase().contains(search.toLowerCase())));
        } else {
            patients = gestionPatients.getPatients(); // Récupération de tous les patients
        }
        model.addAttribute("patients", patients);
        model.addAttribute("search", search);
        return "patients";
    }

    @PostMapping("/add")
    public String addPatient(@RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String situationMedicale) {
        // Utilisation de la facade pour ajouter un patient
        patientFacade.ajouterNouveauPatient(nom, prenom, "", situationMedicale);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        gestionPatients.supprimerPatient(id); // Suppression d'un patient via GestionPatients
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        Patient patient = gestionPatients.getPatients().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
        model.addAttribute("patient", patient);
        return "edit-patient";
    }

    @PostMapping("/update")
    public String updatePatient(@RequestParam Long id,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String situationMedicale) {
        Patient patient = gestionPatients.getPatients().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        gestionPatients.modifierSituationMedicale(patient.getCode(), situationMedicale);
        return "redirect:/patients";
    }
}
