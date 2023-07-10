package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.NegotiationModel;
import com.example.projektsoftwarepraktikum.repository.MessageRepository;
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
    @Autowired
    MessageRepository messageRepository;

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

        List<Double> bestUtilityList= messageRepository.receiversBestCase(selectedOption,partnerID);
        List<Double> worstUtilityList= messageRepository.receiversWorstCase(selectedOption,partnerID);
        System.out.println("SizeUtility"+bestUtilityList.size());
        System.out.println(bestUtilityList.size());
        Double[] bestUtility= bestUtilityList.toArray(new Double[0]);
        Double[] worstUtility= worstUtilityList.toArray(new Double[0]);
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
        System.out.println(bestArray.length);
        //FIXME
        if (bestUtility.length > 0 && selectedAspiration < bestUtility[bestUtility.length - 1] * 100) {
            feedbackAsp="Your Aspiration Level is lower than your current best utility. " +
                    "You can make more compromises to lead the negotiation to an successful end.";
        } else {
            feedbackAsp="Your Aspiration Level is higher than your current best utility. " +
                    "You can insist more on your priorities and goals.";
        }
        if (bestUtility.length > 0 && selectedReservation < bestArray[bestArray.length-1]*100) {
            feedbackRes="Your Reservation Level is lower than your current best utility. " +
                    "Try to achieve your aspiration level and lead the negotiation to an successful end.";
        } else {
            feedbackRes="Your Reservation Level is higher than your current best utility. " +
                    "Try to insist more on your priorities and goals.";
        }
        model.addAttribute("feedbackAsp", feedbackAsp);
        model.addAttribute("feedbackRes", feedbackRes);


        //Feedback 2
        /*
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
                .toArray(Double[]::new);*/
        List<Double> jointUtilityList= messageRepository.receiversBestCase(selectedOption,partnerID);
        List<Double> contractImbalanceList= messageRepository.receiversWorstCase(selectedOption,partnerID);
        System.out.println("JointUtilityList"+jointUtilityList.size());
        System.out.println(contractImbalanceList.size());
        Double[] jointUtility= jointUtilityList.toArray(new Double[0]);
        Double[] contractImbalance= contractImbalanceList.toArray(new Double[0]);
        //Feedback during Negotiation

        for (Double strTemp : jointUtility){
            System.out.println("JOINTUTILITYARRAY: "+strTemp);
        }
        Double[] jointArray = new Double[jointUtility.length/2];
        String[] barColors = new String[jointUtility.length/2];
        String feedbackJointUtility;
        String feedbackContractImbalance;

        for(int i = 0; i<jointUtility.length/2; i++) {

            jointArray[i] = jointUtility[i];
            System.out.println(jointArray[i]);
            if (jointArray[i] >= 1.5) {
                barColors[i] = "rgb(0, 128, 0, 0.5)";
            } else if (jointArray[i] >= 1.4) {
                barColors[i] = "rgb(255, 255, 0, 0.5)";
            } else {
                barColors[i] = "rgb(255, 0, 0, 0.5)";
            }
        }
        System.out.println("Arraylength: "+jointArray.length);
        if (jointArray[jointArray.length-1] >= 1.5) {
            feedbackJointUtility = "The joint utility from your last offer is very good. Both of you will benefit from this negotiation.";
        } else if(jointArray[jointArray.length-1] >= 1.4) {
            feedbackJointUtility = "The joint utility from your last offer is good. The negotiation could lead to benefits for both of you.";
        } else {
            feedbackJointUtility = "The joint utility from your last offer is ok. You can negotiate more to find a solution from which you both benefit more.";
        }

        Double[] contractImbalanceArray = new Double[contractImbalance.length/2];
        String[] barColors2 = new String[contractImbalance.length/2];

        for(int i = 0; i<contractImbalance.length/2; i++) {
            contractImbalanceArray[i] = contractImbalance[i];
            if (contractImbalanceArray[i] <= 0.1) {
                barColors2[i] = "rgb(0, 128, 0, 0.65)";
            } else if (contractImbalanceArray[i] <= 0.3) {
                barColors2[i] = "rgb(255, 255, 0, 0.65)";
            } else {
                barColors2[i] = "rgb(255, 0, 0, 0.65)";
            }
        }

        if (contractImbalanceArray[contractImbalanceArray.length-1] <= 0.1) {
            feedbackContractImbalance ="The contract imbalance from your last offer is very good. Your negotiation is very fair and no one benefits excessively from the others loses.";
        } else if (contractImbalanceArray[contractImbalanceArray.length-1] <= 0.3) {
            feedbackContractImbalance ="The contract imbalance from your last offer is ok. But try to improve your fairness that your partner feels well treated.";
        } else {
            feedbackContractImbalance ="The contract imbalance from your last offer is bad. Your partner might feel like only you benefit from the actual offer.";
        }
        int[] countArray2 = new int[jointArray.length];
        for (int i = 0; i < jointArray.length; i++) {
            countArray2[i] = (i + 1);
        }
        model.addAttribute("countArray2", countArray2);
        model.addAttribute("jointUtility", jointArray);
        model.addAttribute("barColors", barColors);
        model.addAttribute("barColors2", barColors2);
        model.addAttribute("contractImbalance", contractImbalanceArray);
        model.addAttribute("feedbackJointUtility", feedbackJointUtility);
        model.addAttribute("feedbackContractImbalance", feedbackContractImbalance);
        return "feedback";
    }
    @PostMapping("/feedback")
    public String saveData() {

        return "redirect:/feedbackAfter";
    }

}
