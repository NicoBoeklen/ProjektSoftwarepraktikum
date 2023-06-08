package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.Negotiation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegotiationRepository extends JpaRepository<Negotiation, Integer> {
    Negotiation findByID(Integer ID);
}