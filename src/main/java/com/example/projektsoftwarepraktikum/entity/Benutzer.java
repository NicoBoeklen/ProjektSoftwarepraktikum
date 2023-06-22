package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


import java.util.Set;

@Entity
@Table(name = "Benutzer")
public class Benutzer {

    @Id
    @Column(name = "userID")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "benutzer_rolle", joinColumns = @JoinColumn(name = "userID"), inverseJoinColumns = @JoinColumn(name = "rolleID"))
    private Set<Rolle> roles;

    public Benutzer() {
        // empty constructor for Hibernate
    }

    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Rolle> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rolle> rollen) {
        this.roles = rollen;
    }
}
