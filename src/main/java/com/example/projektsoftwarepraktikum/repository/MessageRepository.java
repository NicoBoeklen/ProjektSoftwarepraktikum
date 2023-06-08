package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<NegotiationMessage, Integer> {
    NegotiationMessage findByID(Integer ID);
}
