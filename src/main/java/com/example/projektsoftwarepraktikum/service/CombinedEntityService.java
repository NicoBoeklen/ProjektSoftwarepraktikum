package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.CombinedEntity;
import com.example.projektsoftwarepraktikum.repository.CombinedEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CombinedEntityService {

    @Autowired
    private CombinedEntityRepository combinedEntityRepository;

    public CombinedEntity saveCombinedEntity(final CombinedEntity combinedEntity) {
        return combinedEntityRepository.save(combinedEntity);
    }

    public List<CombinedEntity> findAllCombinedEntities() {
        return  combinedEntityRepository.findAll();
    }

    public void deleteCombinedEntity(final Integer id) {
        combinedEntityRepository.deleteAllById(Collections.singleton(id));
    }
}
