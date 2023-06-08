package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class NegotiationMessage {

    @Id
    @GeneratedValue
    @Column(name = "negotiationMessageID")
    private Integer negotiationMessageId;

    public NegotiationMessage() {
        // empty constructor for Hibernate
    }
}
