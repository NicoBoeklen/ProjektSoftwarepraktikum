package com.example.projektsoftwarepraktikum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminpageController {
    @GetMapping("/admin/home")
    public String showAdmin(Model model) {
    return "adminpage";
    }
}
