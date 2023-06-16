package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.NegotiationModel;
import com.example.projektsoftwarepraktikum.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public NegotiationModel saveNegotiationModel(NegotiationModel model) {
        return modelRepository.save(model);
    }

    public List<NegotiationModel> findAllNegotiationModels() {
        return modelRepository.findAll();
    }

    public NegotiationModel findNegotiationModelById(Integer id) {return modelRepository.getReferenceById(id);}

    public NegotiationModel findByNegotiationId(Integer id) { return modelRepository.findBySelectedNegotiationID(id);}
}