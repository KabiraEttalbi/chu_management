package com.example.chu_management.composite;

import com.example.chu_management.entities.Service;

public class ServiceUnitaire implements ServiceComponent {
    private Service service;

    public ServiceUnitaire(Service service) {
        this.service = service;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Service : " + service.getNom() + " - " + service.getDescription());
    }
}
