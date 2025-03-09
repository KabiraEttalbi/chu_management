package com.example.chu_management.factories;

import com.example.chu_management.entities.Personnel;

public abstract class PersonnelFactory {
    public abstract Personnel creerPersonnel(String nom, String prenom);
}


