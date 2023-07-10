package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CombinedEntity {

    @Id
    private Integer id;

    @OneToOne
    private NegotiationModel negotiationModel;

    @OneToOne
    private NegotiationMessage negotiationMessage;



    public CombinedEntity(NegotiationModel negotiationModel, NegotiationMessage negotiationMessage, Integer id) {
        this.negotiationModel = negotiationModel;
        this.negotiationMessage = negotiationMessage;
        this.id = id;
    }

    public CombinedEntity() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public NegotiationModel getNegotiationModel() {
        return negotiationModel;
    }

    public void setNegotiationModel(NegotiationModel negotiationModel) {
        this.negotiationModel = negotiationModel;
    }

    public NegotiationMessage getNegotiationMessage() {
        return negotiationMessage;
    }

    public void setNegotiationMessage(NegotiationMessage negotiationMessage) {
        this.negotiationMessage = negotiationMessage;
    }
}