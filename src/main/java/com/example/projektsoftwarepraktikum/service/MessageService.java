package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Negotiation;
import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import com.example.projektsoftwarepraktikum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public NegotiationMessage saveNegotiationMessage(NegotiationMessage message) {
        return messageRepository.save(message);
    }

    public List<NegotiationMessage> findAllNegotiationsMessages() {
        return messageRepository.findAll();
    }

    public void saveNegotiationMessageData(final String[] data, Negotiation neg) {
        NegotiationMessage message = new NegotiationMessage();
        message.setNegotiation(neg);
        message.setSenderId(Integer.valueOf(data[1]));
        /*
        .
        .
        .
        Für alle weiteren Spalten der Excel (Attribute von message)

         */
        neg.addMessage(message);
        messageRepository.save(message);
    }

}