package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class NegotiationModel {

    @Id
    private Integer id;

    private Integer selectedNegotiationID;

    public Integer getSelectedNegotiationID() {
        return selectedNegotiationID;
    }

    public void setSelectedNegotiationID (Integer selectedNegotiationID) {
        this.selectedNegotiationID = selectedNegotiationID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
