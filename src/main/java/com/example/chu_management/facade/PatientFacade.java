package com.example.chu_management.facade;

import com.example.chu_management.entities.Patient;
import com.example.chu_management.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientFacade {
    @Autowired
    private PatientService patientService;

    public void ajouterNouveauPatient(String nom, String prenom, String code,String situationMedicale) {
        Patient patient = new Patient(nom, prenom, code, situationMedicale);
        patientService.ajouterPatient(patient);
        System.out.println("Patient ajouté : " + nom + " " + prenom);
    }

    public void afficherDetailsPatient(String code) {
        Patient patient = patientService.obtenirPatientParCode(code);
        if (patient != null) {
            System.out.println("Patient : " + patient.getNom() + " " + patient.getPrenom());
            System.out.println("Code : " + patient.getCode());
            System.out.println("Situation Médicale : " + patient.getSituationMedicale());
        } else {
            System.out.println("Aucun patient trouvé avec le code : " + code);
        }
    }

    public void afficherTousLesPatients() {
        List<Patient> patients = patientService.obtenirTousLesPatients();
        System.out.println("Liste des patients :");
        for (Patient p : patients) {
            System.out.println(p.getCode() + " - " + p.getNom() + " " + p.getPrenom() + " (" + p.getSituationMedicale() + ")");
        }
    }
}
