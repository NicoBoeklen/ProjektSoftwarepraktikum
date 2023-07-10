package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.CombinedEntity;
import com.example.projektsoftwarepraktikum.entity.Negotiation;
import com.example.projektsoftwarepraktikum.repository.CombinedEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CombinedEntitiyService {
    @Autowired
    CombinedEntityRepository combinedEntityRepository;
    public CombinedEntity saveCombinedEntity(CombinedEntity combinedEntity) {

        return combinedEntityRepository.save(combinedEntity);
    }
}
