package com.example.chu_management.factories;

import com.example.chu_management.entities.Infirmier;
import com.example.chu_management.entities.Personnel;

public class InfirmierFactory extends PersonnelFactory {
    @Override
    public Personnel creerPersonnel(String nom, String prenom) {
        return new Infirmier(nom, prenom, 5);
    }
}
