package com.example.projektsoftwarepraktikum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StartNegotiationController {

    @GetMapping("/negotiation")
    public String Negotiation() {
        return "negotiation";

    }

    @PostMapping("/feedback")
        public String Feedback(){
            return "redirect:/feedback";
        }
}



