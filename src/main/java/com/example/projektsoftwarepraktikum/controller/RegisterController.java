package com.example.projektsoftwarepraktikum.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String startRegister() {
        return "register"; //Gibt die Registrierung Seite für den User zurück
    }
}