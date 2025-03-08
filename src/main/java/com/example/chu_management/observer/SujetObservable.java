package com.example.chu_management.observer;

public interface SujetObservable {
    void ajouterObservateur(Observateur observateur);
    void supprimerObservateur(Observateur observateur);
    void notifierObservateurs(String message);
}
