package com.example.chu_management.composite;

import java.util.ArrayList;
import java.util.List;

public class ServiceGroupe implements ServiceComponent {
    private List<ServiceComponent> services = new ArrayList<>();

    public void ajouterService(ServiceComponent service) {
        services.add(service);
    }

    public void supprimerService(ServiceComponent service) {
        services.remove(service);
    }

    @Override
    public void afficherDetails() {
        System.out.println("=== Groupe de Services ===");
        for (ServiceComponent service : services) {
            service.afficherDetails();
        }
    }
}
