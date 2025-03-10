package com.example.chu_management.mediator;

public interface Mediator {
    void envoyerMessage(String message, ServiceCollegue expediteur);

    void ajouterCollegue(ServiceAdministratif sa);

    void ajouterCollegue(ServiceMedical sm);
}
