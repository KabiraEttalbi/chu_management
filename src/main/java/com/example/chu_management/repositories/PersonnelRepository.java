package com.example.chu_management.repositories;

import com.example.chu_management.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    List<Personnel> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrFonctionContainingIgnoreCase(
            String nom, String prenom, String fonction);
}
