package com.example.chu_management.mediator;

public class ServiceMedical extends ServiceCollegue {
    public ServiceMedical(String nom) {
        super(nom);
    }

    @Override
    public void envoyerMessage(String message) {
        System.out.println(nom + " envoie : " + message);
        mediator.envoyerMessage(message, this);
    }

    @Override
    public void recevoirMessage(String message) {
        System.out.println(nom + " a reçu : " + message);
    }
}
