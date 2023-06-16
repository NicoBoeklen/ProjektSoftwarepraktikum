package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
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

    @GetMapping("/feedback")
    public String startFeedback(Model model) {
        //System.out.println(selectedOption);
        //Integer laufendeVerhandlung=null;
        //bei jedem Mal das auf /feedback zugegriffen wird WErt zurück
        //in AJAX request WErt und bei redirect null
       // if(selectedOption!=null){
            //muss nach laufende Verhandlung gefiltert werden im Stream
        //     laufendeVerhandlung=selectedOption;
        //}
        //System.out.println(laufendeVerhandlung);
        Double[] bestUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getSenderId() == userService.getCurrentUser().getUserId())
                .map(n -> n.getSenderBestCase())
                .filter(utility_issue1 -> utility_issue1 != null) .toArray(Double[]::new);
        Double[] worstUtility = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getSenderId() == userService.getCurrentUser().getUserId()).map(n -> n.getSenderWorstCase())
                .filter(utility_issue1 -> utility_issue1 != null).toArray(Double[]::new);
       /* for(Double utilityIssue:bestUtility){
            System.out.println(utilityIssue);
        }*/
        int[] countArray = new int[worstUtility.length];
        for (int i = 0; i < worstUtility.length; i++) {
            countArray[i] = (i + 1);
        }
        model.addAttribute("countArray", countArray);
        model.addAttribute("bestUtility", bestUtility);
        model.addAttribute("worstUtility", worstUtility);
        return "feedback";
    }
/* Eigentlich sauberer Versuch über Post
   Jedoch nicht funktioniert*/

   /*
    public void startFeedback1(@RequestParam("selectedOption") String selectedOption, Model model){
        System.out.println(selectedOption);

    }
*/

}
