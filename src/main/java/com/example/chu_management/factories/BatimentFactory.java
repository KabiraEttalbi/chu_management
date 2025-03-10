package com.example.chu_management.factories;

import com.example.chu_management.entities.Batiment;

public class BatimentFactory {
    public static Batiment createBatiment(String type, String emplacement, String taille, String fonctionnalite, String description) {
        switch (type.toLowerCase()) {
            case "administration":
                return new Batiment("Administration", emplacement, taille, fonctionnalite, description);
            case "urgence":
                return new Batiment("Urgence", emplacement, taille, fonctionnalite, description);
            case "radiologie":
                return new Batiment("Radiologie", emplacement, taille, fonctionnalite, description);
            default:
                return new Batiment(type, emplacement, taille, fonctionnalite, description);
        }
    }
}
