package com.example.chu_management.repositories;

import com.example.chu_management.entities.Patient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByCode(String code);
    List<Patient> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrCodeContainingIgnoreCaseOrSituationMedicaleContainingIgnoreCase(
            String nom, String prenom, String code, String situationMedicale);
}
