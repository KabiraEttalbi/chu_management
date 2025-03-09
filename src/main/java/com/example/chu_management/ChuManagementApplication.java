package com.example.chu_management;

import com.example.chu_management.adapter.AdaptateurBatiment;
import com.example.chu_management.composite.ServiceComponent;
import com.example.chu_management.composite.ServiceGroupe;
import com.example.chu_management.composite.ServiceUnitaire;
import com.example.chu_management.config.DatabaseConnection;
import com.example.chu_management.entities.Batiment;
import com.example.chu_management.entities.Service;
import com.example.chu_management.facade.PatientFacade;
import com.example.chu_management.factories.BatimentFactory;
import com.example.chu_management.mediator.MediatorConcret;
import com.example.chu_management.mediator.ServiceAdministratif;
import com.example.chu_management.mediator.ServiceCollegue;
import com.example.chu_management.mediator.ServiceMedical;
import com.example.chu_management.observer.GestionPatients;
import com.example.chu_management.observer.Observateur;
import com.example.chu_management.observer.ServiceAdministration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class ChuManagementApplication implements CommandLineRunner{
    @Autowired
    private PatientFacade patientFacade;

    @Override
    public void run(String... args) {
        // Ajouter des patients
        // patientFacade.ajouterNouveauPatient("Ali", "Khan", "Stable", "");
        // patientFacade.ajouterNouveauPatient("Sofia", "Mehdi", "Critique", "");

        // Afficher un patient spécifique
        patientFacade.afficherDetailsPatient("P35");

        // Afficher tous les patients
        patientFacade.afficherTousLesPatients();
    }

    public static void main(String[] args) {
        SpringApplication.run(ChuManagementApplication.class, args);

        // Tester la connexion
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn != null) {
            System.out.println("Connexion à la base de données réussie !");
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }

        //Test Adapter

        // Création d'un bâtiment d'urgence
        Batiment urgence = BatimentFactory.createBatiment("urgence");
        System.out.println("Avant adaptation : " + urgence.getFonctionnalite());

        // Adaptation du bâtiment en bloc de chirurgie
        Batiment urgenceEnChirurgie = new AdaptateurBatiment(urgence, "Bloc de Chirurgie");
        System.out.println("Après adaptation : " + urgenceEnChirurgie.getFonctionnalite());

        //Test Composite
        Service service1 = new Service("Radiologie", "Imagerie médicale", null);
        Service service2 = new Service("Urgence", "Traitement rapide des patients", null);
        Service service3 = new Service("Chirurgie", "Opérations médicales", null);

        ServiceComponent s1 = new ServiceUnitaire(service1);
        ServiceComponent s2 = new ServiceUnitaire(service2);
        ServiceComponent s3 = new ServiceUnitaire(service3);

        ServiceGroupe groupe = new ServiceGroupe();
        groupe.ajouterService(s1);
        groupe.ajouterService(s2);
        groupe.ajouterService(s3);

        groupe.afficherDetails();

        //Test Mediator
        MediatorConcret mediator = new MediatorConcret();

        ServiceCollegue serviceMedical = new ServiceMedical("Service Médical");
        ServiceCollegue serviceAdmin = new ServiceAdministratif("Service Administratif");

        mediator.ajouterCollegue(serviceMedical);
        mediator.ajouterCollegue(serviceAdmin);

        serviceMedical.envoyerMessage("Besoin de renforts pour une urgence !");
        serviceAdmin.envoyerMessage("Nous envoyons du personnel immédiatement !");

        //Test Observer
        GestionPatients gestionPatients = new GestionPatients();
        
        // Création des services administratifs qui observent les changements
        Observateur admin1 = new ServiceAdministration("Service Administratif 1");
        Observateur admin2 = new ServiceAdministration("Service Administratif 2");

        // Enregistrement des observateurs
        gestionPatients.ajouterObservateur(admin1);
        gestionPatients.ajouterObservateur(admin2);

        // Ajout d’un patient
        // Patient patient1 = new Patient("Omar", "Said", "Stable", "");
        // gestionPatients.ajouterPatient(patient1);

        // Modification de la situation médicale d’un patient
        gestionPatients.modifierSituationMedicale("P36", "Critique");

        // Suppression d’un observateur et test d’une nouvelle modification
        gestionPatients.supprimerObservateur(admin2);
        gestionPatients.modifierSituationMedicale("P36", "Guéri");

        //Test Facade dans le fichier TestFacade

    }
}
