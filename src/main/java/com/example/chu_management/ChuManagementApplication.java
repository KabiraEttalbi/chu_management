package com.example.chu_management;

import com.example.chu_management.composite.ServiceComponent;
import com.example.chu_management.composite.ServiceGroupe;
import com.example.chu_management.composite.ServiceUnitaire;
import com.example.chu_management.config.DatabaseConnection;
import com.example.chu_management.entities.Service;
import com.example.chu_management.mediator.MediatorConcret;
import com.example.chu_management.mediator.ServiceAdministratif;
import com.example.chu_management.mediator.ServiceCollegue;
import com.example.chu_management.mediator.ServiceMedical;
import com.example.chu_management.observer.GestionPatients;
import com.example.chu_management.observer.Observateur;
import com.example.chu_management.observer.ServiceAdministration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;

@SpringBootApplication
public class ChuManagementApplication {

    public static void main(String[] args) {
        // Initialisation du contexte Spring
        ApplicationContext context = SpringApplication.run(ChuManagementApplication.class, args);

        // Récupérer le bean GestionPatients via ApplicationContext
        GestionPatients gestionPatients = context.getBean(GestionPatients.class);

        // Tester la connexion à la base de données
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn != null) {
            System.out.println("Connexion à la base de données réussie !");
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }

        // Test Composite
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

        // Test Mediator
        MediatorConcret mediator = new MediatorConcret();

        ServiceCollegue serviceMedical = new ServiceMedical("Service Médical");
        ServiceCollegue serviceAdmin = new ServiceAdministratif("Service Administratif");

        mediator.ajouterCollegue(serviceMedical);
        mediator.ajouterCollegue(serviceAdmin);

        serviceMedical.envoyerMessage("Besoin de renforts pour une urgence !");
        serviceAdmin.envoyerMessage("Nous envoyons du personnel immédiatement !");

        // Test Observer
        Observateur admin1 = new ServiceAdministration("Service Administratif 1");
        Observateur admin2 = new ServiceAdministration("Service Administratif 2");

        // Enregistrement des observateurs
        gestionPatients.ajouterObservateur(admin1);
        gestionPatients.ajouterObservateur(admin2);

    }
}
