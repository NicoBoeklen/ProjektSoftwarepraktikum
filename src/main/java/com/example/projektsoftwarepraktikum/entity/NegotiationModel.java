package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class NegotiationModel {

    @Id
    private Integer selectedNegotiationID;

    public Integer getSelectedNegotiationID() {
        return selectedNegotiationID;
    }

    public void setSelectedNegotiationID (Integer selectedNegotiationID) {
        this.selectedNegotiationID = selectedNegotiationID;
    }

}
