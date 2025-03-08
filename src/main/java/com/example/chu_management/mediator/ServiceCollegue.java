package com.example.chu_management.mediator;

public abstract class ServiceCollegue {
    protected Mediator mediator;
    protected String nom;

    public ServiceCollegue(String nom) {
        this.nom = nom;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void envoyerMessage(String message);
    public abstract void recevoirMessage(String message);
}
