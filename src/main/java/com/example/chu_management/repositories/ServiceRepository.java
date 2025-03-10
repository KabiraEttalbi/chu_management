package com.example.chu_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chu_management.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Long>{
    Service findByNom(String nom);
}
