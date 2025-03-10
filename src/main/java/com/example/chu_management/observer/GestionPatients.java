package com.example.chu_management.observer;

import com.example.chu_management.entities.Patient;
import com.example.chu_management.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GestionPatients implements SujetObservable {
    private List<Observateur> observateurs = new ArrayList<>();

    @Autowired
    private PatientRepository patientRepository; // Injection du repository

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
        patientRepository.save(patient); // Sauvegarder le patient dans la base de données
        notifierObservateurs("Nouveau patient ajouté : " + patient.getNom() + " " + patient.getPrenom());
    }

    public void modifierSituationMedicale(String code, String nouvelleSituation) {
        Patient patient = patientRepository.findByCode(code);
        if (patient != null) {
            patient.setSituationMedicale(nouvelleSituation);
            patientRepository.save(patient); // Mettre à jour la situation médicale dans la base
            notifierObservateurs("La situation médicale de " + patient.getNom() + " est devenue " + nouvelleSituation + "!");
        } else {
            System.out.println("Aucun patient trouvé avec le code " + code);
        }
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll(); // Récupérer tous les patients depuis la base de données
    }

    public void supprimerPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            patientRepository.delete(patient); // Suppression du patient de la base de données
            notifierObservateurs("Patient supprimé : " + patient.getNom() + " " + patient.getPrenom());
        } else {
            System.out.println("Aucun patient trouvé avec l'ID : " + id);
        }
    }
    
}
