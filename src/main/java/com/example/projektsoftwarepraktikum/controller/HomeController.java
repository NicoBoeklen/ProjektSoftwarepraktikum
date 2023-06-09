package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.NegotiationModel;
import com.example.projektsoftwarepraktikum.entity.Rolle;
import com.example.projektsoftwarepraktikum.repository.MessageRepository;
import com.example.projektsoftwarepraktikum.repository.RoleRepository;
import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.ModelService;
import com.example.projektsoftwarepraktikum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ModelService modelService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Zeigt die Startseite Ihrer Anwendung.
     *
     * @param model enthält alle ModelAttribute.
     * @return home-Seite.
     */

    @GetMapping("/")
    public String showHome(Model model) {
        boolean isAdmin = userService.getCurrentUser().getRoles().stream()
                .map(Rolle::getId)
                .anyMatch(roleId -> roleRepository.getRolename(roleId).contains("ROLE_ADMIN"));
        if (isAdmin) {
            return "redirect:/admin/home";
        }
        /*for (List<Double> negotiationMessages:messageRepository.getAverageValuesForEachUser()) {
            for (Double averageValues:negotiationMessages
                 ) {
                //System.out.println(averageValues);
            }
            //System.out.println("new Column");
        }*/


        NegotiationModel negModel = new NegotiationModel();
        model.addAttribute("negModel", negModel);
        model.addAttribute("currentUser", userService.getCurrentUser().getUsername());

        List<Integer> negotiationIds=messageRepository.getNegotiationIdQuery(userService.getCurrentUser().getUserId());
        model.addAttribute("userNegotiations", negotiationIds);

        List<String> partnerIds = new ArrayList<>();
        for (Integer negotiatonIdInteger:negotiationIds
             ) {
            List<String> partnerIdLoop= messageRepository.getPartnerQuery(negotiatonIdInteger,userService.getCurrentUser().getUserId());
            partnerIds.addAll(partnerIdLoop);
        }

        model.addAttribute("userPartner", partnerIds);

        //begin als Startdatum und nicht init datum
        List<String> begin = new LinkedList<>();
        Comparator<LocalDateTime> minComparator = new Comparator<LocalDateTime>() {

            @Override
            public int compare(LocalDateTime n1, LocalDateTime n2) {
                return n1.compareTo(n2);
            }
        };
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        for (Integer negotiation : negotiationIds) {
            Optional<LocalDateTime> beginning = messageService.findAllNegotiationsMessages()
                    .stream()
                    .filter(m -> m.getNegotiation().getNegotiationId() == negotiation)
                    .filter(m -> !Objects.equals(m.getMessageType(),"INIT"))
                    .map(n -> n.getSentDate())
                    .min(minComparator);
            begin.add(beginning.get().format(formatter));
        }
        model.addAttribute("beginDate", begin);

        List<String> end = new LinkedList<>();
        for (Integer negotiation : negotiationIds) {
            Optional<LocalDateTime> ending = messageService.findAllNegotiationsMessages()
                    .stream()
                    .filter(m -> m.getNegotiation().getNegotiationId() == negotiation)
                    .map(n -> n.getSentDate())
                    .max(minComparator);
            end.add(ending.get().format(formatter));
        }
        model.addAttribute("endDate", end);
        return "home"; //Gibt die Startseite für den User zurück
    }

    @PostMapping("/")
    public String saveData(@ModelAttribute("negModel") NegotiationModel negModel) {
        Integer selectedID = negModel.getSelectedNegotiationID();
        negModel.setUserId(userService.getCurrentUser().getUserId());
        negModel.setSelectedNegotiationID(selectedID);
        modelService.saveNegotiationModel(negModel);
        return "redirect:/negotiation";
    }
}