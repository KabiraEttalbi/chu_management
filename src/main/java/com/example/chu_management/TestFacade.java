// package com.example.chu_management;

// import com.example.chu_management.facade.PatientFacade;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// @Component
// public class TestFacade implements CommandLineRunner {
//     @Autowired
//     private PatientFacade patientFacade;

//     @Override
//     public void run(String... args) {
//         // Ajouter des patients
//         patientFacade.ajouterNouveauPatient("Ali", "Khan", "P001", "Stable");
//         patientFacade.ajouterNouveauPatient("Sofia", "Mehdi", "P002", "Critique");

//         // Afficher un patient spécifique
//         patientFacade.afficherDetailsPatient("P001");

//         // Afficher tous les patients
//         patientFacade.afficherTousLesPatients();
//     }
// }
