package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedbackController {
   @Autowired
    MessageService messageService;
   @Autowired
    UserService userService;

    @GetMapping("/feedback")
    public String startFeedback() {
        List<Double> UtilityIssues1 = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getSenderId() == userService.getCurrentUser().getUserId())
                .map(n -> n.getUtility_Issue1()).toList();
        return "feedback"; //Gibt die Startseite für den User zurück
    }
}
