package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.NegotiationModel;
import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.ModelService;
import com.example.projektsoftwarepraktikum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StartNegotiationController {
    @Autowired
    private ModelService modelService;

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    private Integer negId;

    @GetMapping("/negotiation")
    public String Negotiation(Model model) {
        model.addAttribute("negModel", modelService.findNegotiationModelByUserId(userService.getCurrentUser().getUserId()));
        negId = modelService.findNegotiationModelByUserId(userService.getCurrentUser().getUserId()).getSelectedNegotiationID();

        List<Double> averageTKIValues = messageService.averageTkiStyleSingleUser();
        for (Double tkiValue : averageTKIValues
        ) {
            System.out.println(tkiValue);
        }
        return "negotiation";

    }

    @PostMapping("/negotiation")
    public String Feedback(@ModelAttribute("negModel") NegotiationModel negModel) {
        negModel.setSelectedNegotiationID(negId);
        negModel.setUserId(userService.getCurrentUser().getUserId());
        modelService.saveNegotiationModel(negModel);
        return "redirect:/feedback";
    }
}



