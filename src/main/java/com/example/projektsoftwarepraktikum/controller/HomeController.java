package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.NegotiationService;
import com.example.projektsoftwarepraktikum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    /**
     * Zeigt die Startseite Ihrer Anwendung.
     * @param model enthält alle ModelAttribute.
     * @return home-Seite.
     */
    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().getUsername());
        model.addAttribute("userNegotiations", messageService.findAllNegotiationsMessages().stream().filter(m -> m.getSenderId()== userService.getCurrentUser().getUserId()).mapToInt(n->n.getNegotiation().getNegotiationId()).distinct().count()).toString();
        return "home"; //Gibt die Startseite für den User zurück
    }

}