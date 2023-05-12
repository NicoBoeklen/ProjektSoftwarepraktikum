package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, Integer> {
    Benutzer findByUsername(String username);
}