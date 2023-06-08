package com.example.projektsoftwarepraktikum.entity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Negotiation {

    @Id
    @Column(name = "negotiationID")
    private Integer negotiationId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "negotiationMessage", joinColumns = @JoinColumn(name = "negotiationID"), inverseJoinColumns = @JoinColumn(name = "negotiationMesssageID"))
    private Set<NegotiationMessage> messages;

    public Negotiation() {
        // empty constructor for Hibernate
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
