package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.Negotiation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegotiationRepository extends JpaRepository<Negotiation, Integer> {
    Negotiation findByNegotiationId(final Integer negotiationId); //findBy must be followed by the actual variable name of the class
}