package com.example.chu_management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.chu_management.observer.GestionPatients;
import com.example.chu_management.observer.Observateur;
import com.example.chu_management.repositories.BatimentRepository;
import com.example.chu_management.repositories.PatientRepository;
import com.example.chu_management.repositories.PersonnelRepository;
import com.example.chu_management.repositories.ServiceRepository;

@Controller
public class DashboardController implements Observateur {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private BatimentRepository batimentRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private GestionPatients gestionPatients;

    private List<String> notifications = new ArrayList<>();

    public DashboardController() {
        // Initialisation de la liste des notifications
        this.notifications = new ArrayList<>();
    }

    @Autowired
    public void registerObserver() {
        gestionPatients.ajouterObservateur(this); // S'inscrire aux notifications de GestionPatients
    }

    @Override
    public void mettreAJour(String message) {
        notifications.add(message); // Ajouter la notification reçue à la liste
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        long totalPatients = patientRepository.count();
        long totalServices = serviceRepository.count();
        long totalBatiments = batimentRepository.count();
        long totalPersonnel = personnelRepository.count();

        model.addAttribute("totalPatients", totalPatients);
        model.addAttribute("totalServices", totalServices);
        model.addAttribute("totalBatiments", totalBatiments);
        model.addAttribute("totalPersonnel", totalPersonnel);

        model.addAttribute("notifications", notifications); // Ajouter les notifications dynamiques

        return "dashboard";
    }
}
