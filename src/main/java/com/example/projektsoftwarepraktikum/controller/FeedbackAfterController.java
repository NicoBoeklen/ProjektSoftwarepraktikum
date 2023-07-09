package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.repository.MessageRepository;
import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.ModelService;
import com.example.projektsoftwarepraktikum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class FeedbackAfterController {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    ModelService modelService;


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

        Integer userID = userService.getCurrentUser().getUserId();
        String selectedTKI = modelService.findNegotiationModelByUserId(userID).getSelectedTKIStyle();
        model.addAttribute("selectedTKI", selectedTKI);

        //Feedback
        int highestIndex = getIndexOfHighestValue(tkiValues);
        String feedback;
        if (Objects.equals(tkiTypes[highestIndex], selectedTKI)) {
            feedback = "Your favoured TKI-Style also matches with your most accurate TKI-Style.";
        } else {
            feedback = "Your favoured TKI-Style does not match with your most accurate TKI-Style.";
        }
        model.addAttribute("feedback", feedback);

        //Feedback 2
        Integer selectedOption = modelService.findNegotiationModelByUserId(userID).getSelectedNegotiationID();
        Integer selectedTime = modelService.findNegotiationModelByUserId(userID).getSelectedNumberOfDays();
        Integer selectedIssue = Integer.valueOf(modelService.findNegotiationModelByUserId(userID).getSelectedIssue());
        //begin als Startdatum und nicht init datum
        Comparator<LocalDateTime> negComparator = new Comparator<LocalDateTime>() {

            @Override
            public int compare(LocalDateTime n1, LocalDateTime n2) {
                return n1.compareTo(n2);
            }
        };
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        Optional<LocalDateTime> begin = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> !Objects.equals(m.getMessageType(), "INIT"))
                .map(n -> n.getSentDate())
                .min(negComparator);
        Optional<LocalDateTime> end = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .map(n -> n.getSentDate())
                .max(negComparator);
        Duration duration = Duration.between(begin.get(), end.get());
        long hours = duration.toHours();
        float days = (float) hours / 24;

        String feedbackTime;
        if (days < selectedTime) {
            feedbackTime = "You finished the negotiation in your preferred time. Congratulations!";
        } else {
            feedbackTime = "You did not finish the negotiation in your preferred time. Maybe try to answer faster next time.";
        }

        List<Integer> messageCount = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getNegotiation().getNegotiationId() == selectedOption)
                .filter(m -> Objects.equals(m.getMessageType(), "ACCEPT"))
                .map(n -> n.getMessageCount())
                .toList();

        //Value aus Accept für most important issue
        String acceptValue = messageService.getEndValueOfMostImportantIssue(selectedOption, selectedIssue);

        String[] issues = new String[]{"Investment","Lawn", "Sponsoring","Training camp","Involvement of the fans"};

        model.addAttribute("feedbackTime", feedbackTime);
        model.addAttribute("messageCount", messageCount.get(0));
        model.addAttribute("accept", acceptValue);
        model.addAttribute("selectedTime", selectedTime);
        model.addAttribute("selectedIssue", issues[selectedIssue]);
        model.addAttribute("days", days);
        model.addAttribute("beginDate", begin.get().format(formatter));
        model.addAttribute("endDate", end.get().format(formatter));

        //SECOND FEEDBACK
        List<Double> listSecondFeedback = messageService.getAveragesAdminFeedback();
        Double[] arraySecondFeedback= listSecondFeedback.toArray(new Double[0]);
        model.addAttribute("arraySecondFeedback",arraySecondFeedback);


        return "feedbackAfter";
    }


    private int getIndexOfHighestValue(Double[] array) {
        double highestValue = array[0];
        int highestIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > highestValue) {
                highestValue = array[i];
                highestIndex = i;
            }
        }
        return highestIndex;
    }
}


