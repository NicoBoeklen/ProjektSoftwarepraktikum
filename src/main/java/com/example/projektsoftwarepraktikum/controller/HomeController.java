package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    /**
     * Zeigt die Startseite Ihrer Anwendung.
     *
     * @param model enthält alle ModelAttribute.
     * @return home-Seite.
     */

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().getUsername());
        List<Integer> negotiationIds = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> m.getSenderId() == userService.getCurrentUser().getUserId())
                .map(n -> n.getNegotiation().getNegotiationId())
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("userNegotiations", negotiationIds);

        List<Integer> partnerIDs = messageService.findAllNegotiationsMessages()
                .stream()
                .filter(m -> negotiationIds.contains(m.getNegotiation().getNegotiationId()))
                .map(n -> n.getSenderId())
                .filter(m -> m != userService.getCurrentUser().getUserId())
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("userPartner", partnerIDs);

        List<String> begin = new LinkedList<>();
        Comparator<LocalDateTime> minComparator = new Comparator<LocalDateTime>() {

            @Override
            public int compare(LocalDateTime n1, LocalDateTime n2) {
                return n1.compareTo(n2);
            }
        };
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        for(Integer negotiation: negotiationIds) {
            Optional<LocalDateTime> beginning = messageService.findAllNegotiationsMessages()
                    .stream()
                    .filter(m -> m.getNegotiation().getNegotiationId()==negotiation)
                    .map(n -> n.getSentDate())
                    .min(minComparator);
            begin.add(beginning.get().format(formatter));
        }
        model.addAttribute("beginDate", begin);
        return "home"; //Gibt die Startseite für den User zurück
    }
}