package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Benutzer;
import com.example.projektsoftwarepraktikum.entity.Negotiation;
import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import com.example.projektsoftwarepraktikum.entity.Rolle;
import com.example.projektsoftwarepraktikum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    public NegotiationMessage saveNegotiationMessage(NegotiationMessage message) {
        return messageRepository.save(message);
    }

    public List<NegotiationMessage> findAllNegotiationsMessages() {
        return messageRepository.findAll();
    }

    public NegotiationMessage saveNegotiationMessageData(final String[] data, Negotiation neg) {
        NegotiationMessage message = new NegotiationMessage();
        message.setNegotiation(neg);
        message.setSenderId(Integer.valueOf(data[1]));
        //für neue SenderID neuen User anlegen
        newUser(Integer.valueOf(data[1]));
        message.setSenderBestCase(Integer.valueOf(data[2]));
        message.setSenderWorstCase(Integer.valueOf(data[3]));
        message.setReceiversBestCase(Integer.valueOf(data[4]));
        message.setReceiversWorstCase(Integer.valueOf(data[5]));
        message.setSentDate(data[6]);
        message.setMessageType(data[7]);
        message.setNegoOutcome(data[8]);
        message.setJointUtilityBest(Integer.valueOf(data[9]));
        message.setJointUtilityWorst(Integer.valueOf(data[10]));
        message.setContractImbalanceBest(Integer.valueOf(data[11]));
        message.setContractImbalanceWorst(Integer.valueOf(data[12]));
        message.setMessageCount(Integer.valueOf(data[13]));
        message.setIssue5(data[14]);
        message.setValue5(data[15]);
        message.setUtility_Issue5(Integer.valueOf(data[16]));
        message.setIssue4(data[17]);
        message.setValue4(data[18]);
        message.setUtility_Issue4(Integer.valueOf(data[19]));
        message.setIssue2(data[20]);
        message.setValue2(data[21]);
        message.setUtility_Issue2(Integer.valueOf(data[22]));
        message.setIssue3(data[23]);
        message.setValue3(data[24]);
        message.setUtility_Issue3(Integer.valueOf(data[25]));
        message.setIssue1(data[26]);
        message.setValue1(data[27]);
        message.setUtility_Issue1(Integer.valueOf(data[28]));
        message.setQ1(Integer.valueOf(data[29]));
        message.setQ2(Integer.valueOf(data[30]));
        message.setQ3(Integer.valueOf(data[31]));
        message.setQ4(Integer.valueOf(data[32]));
        message.setQ5(Integer.valueOf(data[33]));
        message.setTKI_Competing(Integer.valueOf(data[34]));
        message.setTKI_Compromising(Integer.valueOf(data[35]));
        message.setTKI_Collaborating(Integer.valueOf(data[36]));
        message.setTKI_Avoiding(Integer.valueOf(data[37]));
        message.setTKI_Accommodating(Integer.valueOf(data[38]));
        message.setTKI_Competing_Quantile_Abs(Integer.valueOf(data[39]));
        message.setTKI_Compromising_Quantile_Abs(Integer.valueOf(data[40]));
        message.setTKI_Avoiding_Quantile_Abs(Integer.valueOf(data[41]));
        message.setTKI_Accommodating_Quantile_Abs(Integer.valueOf(data[42]));
        message.setTKI_Competing_Quantile_Rel(Integer.valueOf(data[43]));
        message.setTKI_Compromising_Quantile_Rel(Integer.valueOf(data[44]));
        message.setTKI_Collaborating_Quantile_Rel(Integer.valueOf(data[45]));
        message.setTKI_Avoiding_Quantile_Rel(Integer.valueOf(data[46]));
        message.setTKI_Accommodating_Quantile_Rel(Integer.valueOf(data[47]));

        neg.addMessage(message);
        return message;
    }

    private void newUser(Integer userID) {
        //Überprüfen ob bereits User existiert
        if (!userService.existingUser(userID)) {
            Benutzer newUser = new Benutzer();
            newUser.setUserId(userID);
            newUser.setUsername("user" + userID.toString());
            newUser.setPassword(passwordEncoder.encode("1234"));
            Rolle userRole = new Rolle("ROLE_USER");
            roleService.saveRole(userRole);

            Set<Rolle> userRoles = new HashSet<>();
            userRoles.add(userRole);
            newUser.setRoles(userRoles);
            newUser.setUserId(userID);
            userService.saveUser(newUser);
       }
    }
}