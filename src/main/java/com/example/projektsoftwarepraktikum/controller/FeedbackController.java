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
import java.util.Objects;

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
        //Feedback 1
        //Über userId seine Zieleingabe zur Verhandlung abrufen (jeder User nur ein negotiation Model)
        Integer userID = userService.getCurrentUser().getUserId();
        Integer selectedOption = modelService.findNegotiationModelByUserId(userID).getSelectedNegotiationID();
        Integer selectedAspiration = modelService.findNegotiationModelByUserId(userID).getSelectedAspirationLevel();
        Integer selectedReservation = modelService.findNegotiationModelByUserId(userID).getSelectedReservationLevel();
        Integer partnerID = messageService.getPartnerID(userID, selectedOption);

        model.addAttribute("selectedOption", selectedOption);
        model.addAttribute("selectedAspirationLevel", selectedAspiration);
        model.addAttribute("selectedReservationLevel", selectedReservation);

        Double[] bestUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> m.getSenderId() == partnerID)
                .filter(m -> !Objects.equals(m.getMessageType(), "QUESTION") && !Objects.equals(m.getMessageType(),"CLARIFICATION"))
                .map(n -> n.getReceiversBestCase())
                .filter(utility_issue1 -> utility_issue1 != null)
                .toArray(Double[]::new);

        Double[] worstUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> m.getSenderId() == partnerID)
                .filter(m -> !Objects.equals(m.getMessageType(), "QUESTION") && !Objects.equals(m.getMessageType(),"CLARIFICATION"))
                .map(n -> n.getReceiversWorstCase())
                .filter(utility_issue1 -> utility_issue1 != null)
                .toArray(Double[]::new);

        //Feedback during Negotiation
        Double[] bestArray = new Double[bestUtility.length/2];

        for(int i = 0; i<bestUtility.length/2; i++) {
            bestArray[i] = bestUtility[i];
        }

        Double[] worstArray = new Double[worstUtility.length/2];

        for(int i = 0; i<worstUtility.length/2; i++) {
            worstArray[i] = worstUtility[i];
        }

        int[] countArray = new int[worstArray.length];
        for (int i = 0; i < worstArray.length; i++) {
            countArray[i] = (i + 1);
        }
        model.addAttribute("countArray", countArray);
        model.addAttribute("bestUtility", bestArray);
        model.addAttribute("worstUtility", worstArray);

        //Individual Feedback
        String feedbackAsp;
        String feedbackRes;
        if (selectedAspiration < bestArray[bestArray.length-1]*100) {
            feedbackAsp="Your Aspiration Level is lower than your current best utility. " +
                    "You can make more compromises to lead the negotiation to an successful end.";
        } else {
            feedbackAsp="Your Aspiration Level is higher than your current best utility. " +
                    "You can insist more on your priorities and goals.";
        }
        if (selectedReservation < bestArray[bestArray.length-1]*100) {
            feedbackRes="Your Reservation Level is lower than your current best utility. " +
                    "Try to achieve your aspiration level and lead the negotiation to an successful end.";
        } else {
            feedbackRes="Your Reservation Level is higher than your current best utility. " +
                    "Try to insist more on your priorities and goals.";
        }
        model.addAttribute("feedbackAsp", feedbackAsp);
        model.addAttribute("feedbackRes", feedbackRes);


        //Feedback 2
        Double[] jointUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> m.getSenderId() == userID)
                .filter(m -> !Objects.equals(m.getMessageType(), "QUESTION") && !Objects.equals(m.getMessageType(),"CLARIFICATION"))
                .map(n -> n.getJointUtilityBest())
                .filter(utility_issue1 -> utility_issue1 != null)
                .toArray(Double[]::new);

        Double[] contractImbalance = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> m.getSenderId() == userID)
                .filter(m -> !Objects.equals(m.getMessageType(), "QUESTION") && !Objects.equals(m.getMessageType(),"CLARIFICATION"))
                .map(n -> n.getContractImbalanceBest())
                .filter(ci -> ci != null)
                .toArray(Double[]::new);

        //Feedback during Negotiation
        Double[] jointArray = new Double[jointUtility.length/2];
        String[] barColors = new String[jointUtility.length/2];

        for(int i = 0; i<jointUtility.length/2; i++) {
            jointArray[i] = jointUtility[i];
            if (jointArray[i] >= 1.5) {
                barColors[i] = "green";
            } else if (jointArray[i] >= 1.4) {
                barColors[i] = "yellow";
            } else if (jointArray[i] >= 1.3) {
                barColors[i] = "red";
            }
        }

        Double[] contractImbalanceArray = new Double[contractImbalance.length/2];
        String[] barColors2 = new String[contractImbalance.length/2];

        for(int i = 0; i<contractImbalance.length/2; i++) {
            contractImbalanceArray[i] = contractImbalance[i];
            if (contractImbalanceArray[i] <= 0.1) {
                barColors2[i] = "green";
            } else if (contractImbalanceArray[i] <= 0.3) {
                barColors2[i] = "yellow";
            } else if (contractImbalanceArray[i] <= 0.5) {
                barColors2[i] = "red";
            }
        }

        model.addAttribute("jointUtility", jointArray);
        model.addAttribute("barColors", barColors);
        model.addAttribute("barColors2", barColors2);
        model.addAttribute("contractImbalance", contractImbalanceArray);
        return "feedback";
    }
    @PostMapping("/feedback")
    public String saveData() {

        return "redirect:/feedbackAfter";
    }

}
