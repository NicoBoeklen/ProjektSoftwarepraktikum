package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import com.example.projektsoftwarepraktikum.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedbackController {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    ModelService modelService;

    @GetMapping("/feedback")
    public String startFeedback(Model model) {
        Integer selectedOption = modelService.findNegotiationModelById(1).getSelectedNegotiationID();
        Integer selectedAspiration = modelService.findNegotiationModelById(1).getSelectedAspirationLevel();
        model.addAttribute("selectedOption", selectedAspiration);
        model.addAttribute("selectedAspirationLevel", selectedOption);
        Double[] bestUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> m.getSenderId() == userService.getCurrentUser().getUserId())
                .map(n -> n.getSenderBestCase())
                .filter(utility_issue1 -> utility_issue1 != null) .toArray(Double[]::new);
        Double[] worstUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> m.getSenderId() == userService.getCurrentUser().getUserId())
                .map(n -> n.getSenderWorstCase())
                .filter(utility_issue1 -> utility_issue1 != null).toArray(Double[]::new);

        int[] countArray = new int[worstUtility.length];
        for (int i = 0; i < worstUtility.length; i++) {
            countArray[i] = (i + 1);
        }
        model.addAttribute("countArray", countArray);
        model.addAttribute("bestUtility", bestUtility);
        model.addAttribute("worstUtility", worstUtility);
        return "feedback";
    }

}
