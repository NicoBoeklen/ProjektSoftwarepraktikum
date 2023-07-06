package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FeedbackAfterController {
    @Autowired
    MessageService messageService;

    @GetMapping("/feedbackAfter")
    public String startFeedback(Model model) {
        List<Double> tkiList = messageService.averageTkiStyleSingleUser();
        Double[] tkiValues = tkiList.toArray(new Double[0]);

        int[] intArray = Arrays.stream(tkiValues)
                .mapToInt(value -> (int) Math.round(value))
                .toArray();
        model.addAttribute("tkiValuesInt", intArray);
        String[] tkiTypes = new String[]{"Competing", "Compromising", "Collaborating", "Avoiding", "Accommodating"};
        model.addAttribute("tkiValues", tkiValues);
        model.addAttribute("tkiTypes", tkiTypes);
        return "feedbackAfter";
    }
}

