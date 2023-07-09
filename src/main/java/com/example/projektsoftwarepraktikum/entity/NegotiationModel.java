package com.example.projektsoftwarepraktikum.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class NegotiationModel {


    @Id
    private Integer userid;

    private Integer selectedNegotiationID;

    private Integer selectedAspirationLevel;

    private Integer selectedReservationLevel;

    private Integer selectedNumberOfDays;

    private String selectedTKIStyle;

    private String selectedIssue;

    public Integer getSelectedNegotiationID() {
        return selectedNegotiationID;
    }

    public void setSelectedNegotiationID (Integer selectedNegotiationID) {
        this.selectedNegotiationID = selectedNegotiationID;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer id) {
        this.userid = id;
    }

    public Integer getSelectedAspirationLevel() {
        return selectedAspirationLevel;
    }

    public void setSelectedAspirationLevel(Integer selectedAspirationLevel) {
        this.selectedAspirationLevel = selectedAspirationLevel;
    }

    public Integer getSelectedReservationLevel() {
        return selectedReservationLevel;
    }

    public void setSelectedReservationLevel(Integer selectedReservationLevel) {
        this.selectedReservationLevel = selectedReservationLevel;
    }

    public Integer getSelectedNumberOfDays() {
        return selectedNumberOfDays;
    }

    public void setSelectedNumberOfDays(Integer selectedNumberOfDays) {
        this.selectedNumberOfDays = selectedNumberOfDays;
    }

    public String getSelectedTKIStyle() {
        return selectedTKIStyle;
    }

    public void setSelectedTKIStyle(String selectedTKIStyle) {
        this.selectedTKIStyle = selectedTKIStyle;
    }

    public String getSelectedIssue() {
        return selectedIssue;
    }

    public void setSelectedIssue(String selectedIssue) {
        this.selectedIssue = selectedIssue;
    }
}
