package com.example.chu_management.factories;

import com.example.chu_management.entities.Batiment;

public class BatimentFactory {
    public static Batiment createBatiment(String type) {
        return switch (type.toLowerCase()) {
            case "administration" -> new Batiment("Administration", "Bloc A", 200, "Gestion", "Administration du CHU");
            case "urgence" -> new Batiment("Urgence", "Bloc B", 300, "Urgence", "Service d'urgence 24/7");
            case "radiologie" -> new Batiment("Radiologie", "Bloc C", 150, "Imagerie", "Radiographies et scanners");
            default -> throw new IllegalArgumentException("Type de bâtiment inconnu : " + type);
        };
    }
}
