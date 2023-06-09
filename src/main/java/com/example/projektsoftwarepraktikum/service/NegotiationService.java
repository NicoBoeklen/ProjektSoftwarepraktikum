package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Negotiation;
import com.example.projektsoftwarepraktikum.repository.NegotiationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NegotiationService {
    @Autowired
    private NegotiationRepository negotiationRepository;

    public Negotiation saveNegotiation(Negotiation negotiation) {
        return negotiationRepository.save(negotiation);
    }

    public List<Negotiation> findAllNegotiations() {
        return negotiationRepository.findAll();
    }
}
