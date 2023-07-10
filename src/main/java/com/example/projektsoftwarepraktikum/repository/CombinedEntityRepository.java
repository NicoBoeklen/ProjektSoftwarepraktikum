package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.CombinedEntity;
import com.example.projektsoftwarepraktikum.entity.Rolle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CombinedEntityRepository extends JpaRepository<CombinedEntity, Integer> {
    CombinedEntity findByid(Integer id);

    @Query("SELECT ce.negotiationModel.userid FROM CombinedEntity ce")
    List<CombinedEntity> getCombined();
}
