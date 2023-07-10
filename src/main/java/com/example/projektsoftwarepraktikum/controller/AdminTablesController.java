package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminTablesController {

    @Autowired
    MessageService messageService;
    @GetMapping("/admin/tables")
    public String showAdmin(Model model) {

    return "adminTables";
    }
}
