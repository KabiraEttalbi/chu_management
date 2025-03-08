package com.example.chu_management.factories;

import com.example.chu_management.entities.Medecin;
import com.example.chu_management.entities.Infirmier;
import com.example.chu_management.entities.Personnel;

public abstract class PersonnelFactory {
    public abstract Personnel creerPersonnel(String nom, String prenom);
}

class MedecinFactory extends PersonnelFactory {
    @Override
    public Personnel creerPersonnel(String nom, String prenom) {
        return new Medecin(nom, prenom, "Généraliste");
    }
}

class InfirmierFactory extends PersonnelFactory {
    @Override
    public Personnel creerPersonnel(String nom, String prenom) {
        return new Infirmier(nom, prenom, 5);
    }
}
