package com.example.chu_management.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("INFIRMIER")
public class Infirmier extends Personnel {
    private int anneeExperience;

    public Infirmier() {
    }

    public Infirmier(String nom, String prenom, int anneeExperience) {
        super(nom, prenom, "Infirmier");
        this.anneeExperience = anneeExperience;
    }

    public int getAnneeExperience() {
        return anneeExperience;
    }

    public void setAnneeExperience(int anneeExperience) {
        this.anneeExperience = anneeExperience;
    }
}
