package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.NegotiationModel;
import com.example.projektsoftwarepraktikum.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class StartNegotiationController {
    @Autowired
    private ModelService modelService;

    @GetMapping("/negotiation")
    public String Negotiation(Model model) {
        model.addAttribute("negModel", modelService.findNegotiationModelById(1));
        return "negotiation";

    }

    @PostMapping("/negotiation")
    public String Feedback(@ModelAttribute("negModel") NegotiationModel negModel) {
        Integer selectedID = negModel.getSelectedNegotiationID();
        negModel.setSelectedNegotiationID(selectedID);
        negModel.setId(2);
        modelService.saveNegotiationModel(negModel);
        return "redirect:/feedback";
    }
}



