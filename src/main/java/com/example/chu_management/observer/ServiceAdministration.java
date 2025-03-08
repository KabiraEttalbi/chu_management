package com.example.chu_management.observer;

public class ServiceAdministration implements Observateur {
    private String nom;

    public ServiceAdministration(String nom) {
        this.nom = nom;
    }

    @Override
    public void mettreAJour(String message) {
        System.out.println("🔔 " + nom + " a reçu une notification : " + message);
    }
}
