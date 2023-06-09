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
        /*
        .
        .
        .
        Für alle weiteren Spalten der Excel (Attribute von message)

         */
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