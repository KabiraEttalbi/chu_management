package com.example.chu_management.services;

import com.example.chu_management.entities.Patient;
import com.example.chu_management.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient ajouterPatient(Patient patient) {
        // Now, set the code using the generated ID
        patient.setCode("P" + patient.getId());
        // Save the patient again to update the code
        return patientRepository.save(patient);
    }

    public Patient obtenirPatientParCode(String code) {
        return patientRepository.findByCode(code);
    }

    public List<Patient> obtenirTousLesPatients() {
        return patientRepository.findAll();
    }

    public void supprimerPatient(Long id) {
        patientRepository.deleteById(id);
    }
}