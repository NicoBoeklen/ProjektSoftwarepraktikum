package com.example.projektsoftwarepraktikum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {
    @GetMapping("/aboutus")
    public String AboutUs() {
        return "aboutus"; //Gibt die Registrierung Seite für den User zurück
    }
}