package com.example.chu_management.controllers;

import com.example.chu_management.entities.Patient;
import com.example.chu_management.repositories.PatientRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public String listPatients(@RequestParam(required = false) String search, Model model) {
        List<Patient> patients;
        if (search != null && !search.isEmpty()) {
            patients = patientRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrCodeContainingIgnoreCaseOrSituationMedicaleContainingIgnoreCase(
                    search, search, search, search);
        } else {
            patients = patientRepository.findAll();
        }
        model.addAttribute("patients", patients);
        model.addAttribute("search", search);
        return "patients";
    }

    @PostMapping("/add")
    public String addPatient(@RequestParam String nom,
                             @RequestParam String prenom,
                             @RequestParam String situationMedicale) {
        Patient patient = new Patient(nom, prenom, situationMedicale);
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientRepository.findById(id).orElse(null));
        return "edit-patient";
    }

    @PostMapping("/update")
    public String updatePatient(@RequestParam Long id,
                                @RequestParam String nom,
                                @RequestParam String prenom,
                                @RequestParam String situationMedicale) {
        patientRepository.findById(id).ifPresent(patient -> {
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setSituationMedicale(situationMedicale);
            patientRepository.save(patient);
        });
        return "redirect:/patients";
    }
}
