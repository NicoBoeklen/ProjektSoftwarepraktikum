package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
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
    public String startFeedback(Model model) {
        Double[] utilityIssues = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getSenderId() == userService.getCurrentUser().getUserId()).map(n -> n.getUtility_Issue1())
                .filter(utility_issue1 -> utility_issue1 != null).toArray(Double[]::new);

        for(Double utilityIssue:utilityIssues){
            System.out.println(utilityIssue);
        }
        model.addAttribute("UtilityList",utilityIssues);
        return "feedback"; //Gibt die Startseite für den User zurück
    }
}
