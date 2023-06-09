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
}