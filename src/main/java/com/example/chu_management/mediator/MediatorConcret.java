package com.example.chu_management.mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorConcret implements Mediator {
    private List<ServiceCollegue> collegues = new ArrayList<>();

    public void ajouterCollegue(ServiceCollegue collegue) {
        collegues.add(collegue);
        collegue.setMediator(this);
    }

    @Override
    public void envoyerMessage(String message, ServiceCollegue expediteur) {
        for (ServiceCollegue collegue : collegues) {
            if (collegue != expediteur) {
                collegue.recevoirMessage(message);
            }
        }
    }

    @Override
    public void ajouterCollegue(ServiceAdministratif sa) {
        collegues.add(sa);
        sa.setMediator(this);
    }

    @Override
    public void ajouterCollegue(ServiceMedical sm) {
        collegues.add(sm);
        sm.setMediator(this);
    }
}

