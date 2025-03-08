package com.example.chu_management.observer;

import com.example.chu_management.entities.Patient;
import java.util.ArrayList;
import java.util.List;

public class GestionPatients implements SujetObservable {
    private List<Observateur> observateurs = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    @Override
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void supprimerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    }

    @Override
    public void notifierObservateurs(String message) {
        for (Observateur o : observateurs) {
            o.mettreAJour(message);
        }
    }

    public void ajouterPatient(Patient patient) {
        patients.add(patient);
        notifierObservateurs("Nouveau patient ajouté : " + patient.getNom() + " " + patient.getPrenom());
    }

    public void modifierSituationMedicale(String code, String nouvelleSituation) {
        for (Patient p : patients) {
            if (p.getCode().equals(code)) {
                p.setSituationMedicale(nouvelleSituation);
                notifierObservateurs("Mise à jour de la situation médicale de " + p.getNom() + " : " + nouvelleSituation);
                return;
            }
        }
        System.out.println("Aucun patient trouvé avec le code " + code);
    }
}
