package com.example.projektsoftwarepraktikum.entity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "negotiation")
public class Negotiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "negotiationID")
    private Integer negotiationId;

    @OneToMany(mappedBy = "negotiation")
    //@JoinTable(name = "negotiationMessage", joinColumns = @JoinColumn(name = "negotiationID"), inverseJoinColumns = @JoinColumn(name = "negotiationMessageID"))
    private Set<NegotiationMessage> messages;

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
