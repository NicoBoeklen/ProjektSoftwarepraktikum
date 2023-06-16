package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.NegotiationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<NegotiationModel, Integer> {
    NegotiationModel findBySelectedNegotiationID(Integer selectedNegotiationID);
}