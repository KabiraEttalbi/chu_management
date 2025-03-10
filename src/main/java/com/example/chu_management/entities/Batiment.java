package com.example.chu_management.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "batiments")
public class Batiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String emplacement;
    private String taille;
    private String fonctionnalite;
    private String description;

    // Constructeurs
    public Batiment() {
    }

    public Batiment(String nom, String emplacement, String taille, String fonctionnalite, String description) {
        this.nom = nom;
        this.emplacement = emplacement;
        this.taille = taille;
        this.fonctionnalite = fonctionnalite;
        this.description = description;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getFonctionnalite() {
        return fonctionnalite;
    }

    public void setFonctionnalite(String fonctionnalite) {
        this.fonctionnalite = fonctionnalite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
