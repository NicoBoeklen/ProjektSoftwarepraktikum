package com.example.projektsoftwarepraktikum.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FeedbackController {
    @GetMapping("/feedback")
    public String startFeedback() {
        return "feedback"; //Gibt die Startseite für den User zurück
    }
}
