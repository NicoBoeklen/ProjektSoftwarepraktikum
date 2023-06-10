package com.example.projektsoftwarepraktikum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartNegotiationController {
    @GetMapping("/Negotiation")
    public String Negotiation() {
        return "Negotiation";
    }
}
