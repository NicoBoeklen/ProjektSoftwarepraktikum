package com.example.projektsoftwarepraktikum.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class CombinedEntity {
    @Autowired
    private NegotiationModel negotiationModel;
    @Autowired
    private NegotiationMessage negotiationMessage;

    public CombinedEntity(NegotiationModel negotiationModel, NegotiationMessage negotiationMessage) {
        this.negotiationModel = negotiationModel;
        this.negotiationMessage = negotiationMessage;
    }


}