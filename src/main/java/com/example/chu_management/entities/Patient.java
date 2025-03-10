package com.example.chu_management.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String code;
    private String situationMedicale;

    public Patient() {
    }

    public Patient(String nom, String prenom, String code, String situationMedicale) {
        this.nom = nom;
        this.prenom = prenom;
        this.code = code;
        this.situationMedicale = situationMedicale;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getSituationMedicale() {
        return situationMedicale;
    }

    public void setSituationMedicale(String situationMedicale) {
        this.situationMedicale = situationMedicale;
    }
}
