package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FeedbackAfterController {
    @Autowired
    MessageService messageService;

    @GetMapping("/feedbackAfter")
    public String startFeedback(Model model) {
        List<Double> tkiList = messageService.averageTkiStyleSingleUser();
        Double[] tkiValues = tkiList.toArray(new Double[0]);
        model.addAttribute("tkiValues", tkiValues);
        return "feedbackAfter";
    }
}

