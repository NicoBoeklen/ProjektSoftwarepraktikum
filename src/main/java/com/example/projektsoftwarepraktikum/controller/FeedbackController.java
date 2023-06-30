package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.ModelService;
import com.example.projektsoftwarepraktikum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
                .filter(m -> m.getMessageType() != "QUESTION" && m.getMessageType() != "CLARIFICATION")
                .map(n -> n.getReceiversBestCase())
                .filter(utility_issue1 -> utility_issue1 != null) .toArray(Double[]::new);

        Double[] worstUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> m.getSenderId() == partnerID)
                .filter(m -> m.getMessageType() != "QUESTION" && m.getMessageType() != "CLARIFICATION")
                .map(n -> n.getReceiversWorstCase())
                .filter(utility_issue1 -> utility_issue1 != null).toArray(Double[]::new);
        
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
        System.out.println(selectedAspiration);
        System.out.println(selectedReservation);
        System.out.println(bestUtility[bestArray.length-1]);
        if (selectedAspiration < bestUtility[bestArray.length-1]) {
            feedbackAsp="Your Aspiration Level is lower than your current best utility. " +
                    "You can make more compromises to lead the negotiation to an successful end.";
        } else {
            feedbackAsp="Your Aspiration Level is higher than your current best utility. " +
                    "You can insist more on your priorities and goals.";
        }
        if (selectedReservation < bestUtility[bestArray.length-1]) {
            feedbackRes="Your Reservation Level is lower than your current best utility. " +
                    "You can make more compromises to lead the negotiation to an successful end.";
        } else {
            feedbackRes="Your Reservation Level is higher than your current best utility. " +
                    "Try to insist more on your priorities and goals or.";
        }
        model.addAttribute("feedbackAsp", feedbackAsp);
        model.addAttribute("feedbackRes", feedbackRes);
        return "feedback";
    }

}
