package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "negotiation")
public class Negotiation {

    @Id
    @Column(name = "negotiationID")
    private Integer negotiationId;

    @OneToMany(mappedBy = "negotiation")
    private Set<NegotiationMessage> messages = new HashSet<>();

    public Negotiation() {
        // empty constructor for Hibernate
    }

    public void addMessage(NegotiationMessage message) {
        this.messages.add(message);
    }
    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////
    public Set<NegotiationMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<NegotiationMessage> messages) {
        this.messages = messages;
    }

    public Integer getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Integer negotiationId) {
        this.negotiationId = negotiationId;
    }

}
