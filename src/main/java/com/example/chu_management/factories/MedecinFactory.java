package com.example.chu_management.factories;

import com.example.chu_management.entities.Medecin;
import com.example.chu_management.entities.Personnel;

public class MedecinFactory extends PersonnelFactory {
    @Override
    public Personnel creerPersonnel(String nom, String prenom) {
        return new Medecin(nom, prenom, "Généraliste");
    }
}