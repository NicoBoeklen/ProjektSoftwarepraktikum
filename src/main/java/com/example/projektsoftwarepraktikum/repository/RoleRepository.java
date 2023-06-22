package com.example.projektsoftwarepraktikum.repository;


import com.example.projektsoftwarepraktikum.entity.Rolle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Rolle, Integer> {
    Rolle findByRolename(String rolename);
}