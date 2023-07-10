package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.CombinedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CombinedEntityRepository extends JpaRepository<CombinedEntity, Integer> {

    @Query("SELECT ce.negotiationModel.userid FROM CombinedEntity ce")
    List<CombinedEntity> getCombined();
}
