package com.example.projektsoftwarepraktikum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table (name = "negotiationMessage")
public class NegotiationMessage {

    @Id
    @GeneratedValue
    @Column(name = "negotiationMessageID")
    private Integer negotiationMessageId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "negotiationID")
    private Negotiation negotiation;

    private Integer senderId;

    private Double senderBestCase;

    private Double senderWorstCase;

    private Double receiversBestCase;

    private Double receiversWorstCase;

    private LocalDateTime sentDate;

    private String messageType;

    private String negoOutcome;

    private Double jointUtilityBest;

    private Double jointUtilityWorst;

    private Double contractImbalanceBest;

    private Double contractImbalanceWorst;

    private Integer messageCount;

    private String Issue5;

    private String Value5;

    private Double Utility_Issue5;

    private String Issue4;

    private String Value4;

    private Double Utility_Issue4;

    private String Issue2;

    private String Value2;

    private Double Utility_Issue2;

    private String Issue3;

    private String Value3;

    private Double Utility_Issue3;

    private String Issue1;

    private String Value1;

    private Double Utility_Issue1;

    private Integer Q1;

    private Integer Q2;

    private Integer Q3;

    private Integer Q4;

    private Integer Q5;

    private Integer TKI_Competing;

    private Integer TKI_Compromising;

    private Integer TKI_Collaborating;

    private Integer TKI_Avoiding;

    private Integer TKI_Accommodating;

    private Integer TKI_Competing_Quantile_Abs;

    private Integer TKI_Compromising_Quantile_Abs;

    private Integer TKI_Avoiding_Quantile_Abs;
    private Integer TKI_Collaborating_Quantile_Abs;

    private Integer TKI_Accommodating_Quantile_Abs;

    private Double TKI_Competing_Quantile_Rel;

    private Double TKI_Compromising_Quantile_Rel;

    private Double TKI_Collaborating_Quantile_Rel;

    private Double TKI_Avoiding_Quantile_Rel;

    private Double TKI_Accommodating_Quantile_Rel;


    public NegotiationMessage() {
        // empty constructor for Hibernate
    }

    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    public Integer getNegotiationMessageId() {
        return negotiationMessageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Double getSenderBestCase() {
        return senderBestCase;
    }

    public void setSenderBestCase(Double senderBestCase) {
        this.senderBestCase = senderBestCase;
    }

    public Double getSenderWorstCase() {
        return senderWorstCase;
    }

    public void setSenderWorstCase(Double senderWorstCase) {
        this.senderWorstCase = senderWorstCase;
    }

    public Double getReceiversBestCase() {
        return receiversBestCase;
    }

    public void setReceiversBestCase(Double receiversBestCase) {
        this.receiversBestCase = receiversBestCase;
    }

    public Double getReceiversWorstCase() {
        return receiversWorstCase;
    }

    public void setReceiversWorstCase(Double receiversWorstCase) {
        this.receiversWorstCase = receiversWorstCase;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getNegoOutcome() {
        return negoOutcome;
    }

    public void setNegoOutcome(String negoOutcome) {
        this.negoOutcome = negoOutcome;
    }

    public Double getJointUtilityBest() {
        return jointUtilityBest;
    }

    public void setJointUtilityBest(Double jointUtilityBest) {
        this.jointUtilityBest = jointUtilityBest;
    }

    public Double getJointUtilityWorst() {
        return jointUtilityWorst;
    }

    public void setJointUtilityWorst(Double jointUtilityWorst) {
        this.jointUtilityWorst = jointUtilityWorst;
    }

    public Double getContractImbalanceBest() {
        return contractImbalanceBest;
    }

    public void setContractImbalanceBest(Double contractImbalanceBest) {
        this.contractImbalanceBest = contractImbalanceBest;
    }

    public Double getContractImbalanceWorst() {
        return contractImbalanceWorst;
    }

    public void setContractImbalanceWorst(Double contractImbalanceWorst) {
        this.contractImbalanceWorst = contractImbalanceWorst;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public String getIssue5() {
        return Issue5;
    }

    public void setIssue5(String issue5) {
        Issue5 = issue5;
    }

    public String getValue5() {
        return Value5;
    }

    public void setValue5(String value5) {
        Value5 = value5;
    }

    public Double getUtility_Issue5() {
        return Utility_Issue5;
    }

    public void setUtility_Issue5(Double utility_Issue5) {
        Utility_Issue5 = utility_Issue5;
    }

    public String getIssue4() {
        return Issue4;
    }

    public void setIssue4(String issue4) {
        Issue4 = issue4;
    }

    public String getValue4() {
        return Value4;
    }

    public void setValue4(String value4) {
        Value4 = value4;
    }

    public Double getUtility_Issue4() {
        return Utility_Issue4;
    }

    public void setUtility_Issue4(Double utility_Issue4) {
        Utility_Issue4 = utility_Issue4;
    }

    public String getIssue2() {
        return Issue2;
    }

    public void setIssue2(String issue2) {
        Issue2 = issue2;
    }

    public String getValue2() {
        return Value2;
    }

    public void setValue2(String value2) {
        Value2 = value2;
    }

    public Double getUtility_Issue2() {
        return Utility_Issue2;
    }

    public void setUtility_Issue2(Double utility_Issue2) {
        Utility_Issue2 = utility_Issue2;
    }

    public String getIssue3() {
        return Issue3;
    }

    public void setIssue3(String issue3) {
        Issue3 = issue3;
    }

    public String getValue3() {
        return Value3;
    }

    public void setValue3(String value3) {
        Value3 = value3;
    }

    public Double getUtility_Issue3() {
        return Utility_Issue3;
    }

    public void setUtility_Issue3(Double utility_Issue3) {
        Utility_Issue3 = utility_Issue3;
    }

    public String getIssue1() {
        return Issue1;
    }

    public void setIssue1(String issue1) {
        Issue1 = issue1;
    }

    public String getValue1() {
        return Value1;
    }

    public void setValue1(String value1) {
        Value1 = value1;
    }

    public Double getUtility_Issue1() {
        return Utility_Issue1;
    }

    public void setUtility_Issue1(Double utility_Issue1) {
        Utility_Issue1 = utility_Issue1;
    }

    public Integer getQ1() {
        return Q1;
    }

    public void setQ1(Integer q1) {
        Q1 = q1;
    }

    public Integer getQ2() {
        return Q2;
    }

    public void setQ2(Integer q2) {
        Q2 = q2;
    }

    public Integer getQ3() {
        return Q3;
    }

    public void setQ3(Integer q3) {
        Q3 = q3;
    }

    public Integer getQ4() {
        return Q4;
    }

    public void setQ4(Integer q4) {
        Q4 = q4;
    }

    public Integer getQ5() {
        return Q5;
    }

    public void setQ5(Integer q5) {
        Q5 = q5;
    }

    public Integer getTKI_Competing() {
        return TKI_Competing;
    }

    public void setTKI_Competing(Integer TKI_Competing) {
        this.TKI_Competing = TKI_Competing;
    }

    public Integer getTKI_Compromising() {
        return TKI_Compromising;
    }

    public void setTKI_Compromising(Integer TKI_Compromising) {
        this.TKI_Compromising = TKI_Compromising;
    }

    public Integer getTKI_Collaborating() {
        return TKI_Collaborating;
    }

    public void setTKI_Collaborating(Integer TKI_Collaborating) {
        this.TKI_Collaborating = TKI_Collaborating;
    }

    public Integer getTKI_Avoiding() {
        return TKI_Avoiding;
    }

    public void setTKI_Avoiding(Integer TKI_Avoiding) {
        this.TKI_Avoiding = TKI_Avoiding;
    }

    public Integer getTKI_Accommodating() {
        return TKI_Accommodating;
    }

    public void setTKI_Accommodating(Integer TKI_Accommodating) {
        this.TKI_Accommodating = TKI_Accommodating;
    }

    public Integer getTKI_Competing_Quantile_Abs() {
        return TKI_Competing_Quantile_Abs;
    }

    public void setTKI_Competing_Quantile_Abs(Integer TKI_Competing_Quantile_Abs) {
        this.TKI_Competing_Quantile_Abs = TKI_Competing_Quantile_Abs;
    }

    public Integer getTKI_Compromising_Quantile_Abs() {
        return TKI_Compromising_Quantile_Abs;
    }

    public void setTKI_Compromising_Quantile_Abs(Integer TKI_Compromising_Quantile_Abs) {
        this.TKI_Compromising_Quantile_Abs = TKI_Compromising_Quantile_Abs;
    }
    public void setTKI_Collaborating_Quantile_Abs(Integer TKI_Collaborating_Quantile_Abs) {
        this.TKI_Collaborating_Quantile_Abs = TKI_Collaborating_Quantile_Abs;
    }
    public Integer getTKI_Avoiding_Quantile_Abs() {
        return TKI_Avoiding_Quantile_Abs;
    }

    public void setTKI_Avoiding_Quantile_Abs(Integer TKI_Avoiding_Quantile_Abs) {
        this.TKI_Avoiding_Quantile_Abs = TKI_Avoiding_Quantile_Abs;
    }

    public Integer getTKI_Accommodating_Quantile_Abs() {
        return TKI_Accommodating_Quantile_Abs;
    }

    public void setTKI_Accommodating_Quantile_Abs(Integer TKI_Accommodating_Quantile_Abs) {
        this.TKI_Accommodating_Quantile_Abs = TKI_Accommodating_Quantile_Abs;
    }

    public Double getTKI_Competing_Quantile_Rel() {
        return TKI_Competing_Quantile_Rel;
    }

    public void setTKI_Competing_Quantile_Rel(Double TKI_Competing_Quantile_Rel) {
        this.TKI_Competing_Quantile_Rel = TKI_Competing_Quantile_Rel;
    }

    public Double getTKI_Compromising_Quantile_Rel() {
        return TKI_Compromising_Quantile_Rel;
    }

    public void setTKI_Compromising_Quantile_Rel(Double TKI_Compromising_Quantile_Rel) {
        this.TKI_Compromising_Quantile_Rel = TKI_Compromising_Quantile_Rel;
    }

    public Double getTKI_Collaborating_Quantile_Rel() {
        return TKI_Collaborating_Quantile_Rel;
    }

    public void setTKI_Collaborating_Quantile_Rel(Double TKI_Collaborating_Quantile_Rel) {
        this.TKI_Collaborating_Quantile_Rel = TKI_Collaborating_Quantile_Rel;
    }

    public Double getTKI_Avoiding_Quantile_Rel() {
        return TKI_Avoiding_Quantile_Rel;
    }

    public void setTKI_Avoiding_Quantile_Rel(Double TKI_Avoiding_Quantile_Rel) {
        this.TKI_Avoiding_Quantile_Rel = TKI_Avoiding_Quantile_Rel;
    }

    public Double getTKI_Accommodating_Quantile_Rel() {
        return TKI_Accommodating_Quantile_Rel;
    }

    public void setTKI_Accommodating_Quantile_Rel(Double TKI_Accommodating_Quantile_Rel) {
        this.TKI_Accommodating_Quantile_Rel = TKI_Accommodating_Quantile_Rel;
    }
}
