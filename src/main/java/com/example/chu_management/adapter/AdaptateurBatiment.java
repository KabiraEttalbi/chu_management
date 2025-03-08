package com.example.chu_management.adapter;

import com.example.chu_management.entities.Batiment;

public class AdaptateurBatiment extends Batiment {
    @SuppressWarnings("unused")
    private Batiment batimentExistant;

    public AdaptateurBatiment(Batiment batimentExistant, String nouvelleFonctionnalite) {
        super(batimentExistant.getNom(), batimentExistant.getEmplacement(), batimentExistant.getTaille(), nouvelleFonctionnalite, batimentExistant.getDescription());
        this.batimentExistant = batimentExistant;
    }

    @Override
    public String getFonctionnalite() {
        return "Adapté pour : " + super.getFonctionnalite();
    }
}
