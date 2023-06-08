package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rolle")
public class Rolle {

    @Id
    @GeneratedValue
    @Column(name = "rolleID")
    private Integer id;

    @Column(name = "rolename")
    private String rolename;

    public Rolle() {
        //empty constructor for Hibernate
    }

    public Rolle(String rolename) {
        this.rolename = rolename;
    }

    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
