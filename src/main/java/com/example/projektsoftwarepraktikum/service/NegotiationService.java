package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Negotiation;
import com.example.projektsoftwarepraktikum.repository.NegotiationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class NegotiationService {
    @Autowired
    private NegotiationRepository negotiationRepository;

    @Autowired
    private MessageService messageService;

    String line = "";

    public Negotiation saveNegotiation(Negotiation negotiation) {
        return negotiationRepository.save(negotiation);
    }

    public List<Negotiation> findAllNegotiations() {
        return negotiationRepository.findAll();
    }

    public void saveNegotiationData() {
    int counter = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Testdaten_TKI.csv"));
            Negotiation neg = null;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (counter >= 1) {    //Skip first row
                    if (negotiationRepository.findByNegotiationId(Integer.valueOf(data[0])) == null) {
                        //New Negotiation if negotiationID does not exist
                        neg = new Negotiation();
                        neg.setNegotiationId(Integer.valueOf(data[0]));
                        negotiationRepository.save(neg);
                    }
                }
                //Save Messages
                messageService.saveNegotiationMessage(messageService.saveNegotiationMessageData(data, neg));
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
