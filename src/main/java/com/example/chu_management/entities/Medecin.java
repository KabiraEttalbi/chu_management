package com.example.chu_management.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MEDECIN")
public class Medecin extends Personnel {
    private String specialite;

    public Medecin() {
    }

    public Medecin(String nom, String prenom, String specialite) {
        super(nom, prenom, "Médecin");
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
