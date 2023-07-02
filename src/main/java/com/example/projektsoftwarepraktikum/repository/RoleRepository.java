package com.example.projektsoftwarepraktikum.repository;


import com.example.projektsoftwarepraktikum.entity.Rolle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Rolle, Integer> {
    Rolle findByRolename(String rolename);
    @Query("SELECT r.rolename FROM Rolle r WHERE r.id = :roleId ")
    List<String> getRolename(@Param("roleId") Integer roleId );
}