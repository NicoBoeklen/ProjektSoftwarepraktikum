package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.entity.CombinedEntity;
import com.example.projektsoftwarepraktikum.repository.CombinedEntityRepository;
import com.example.projektsoftwarepraktikum.service.MessageService;
import com.example.projektsoftwarepraktikum.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    private MessageService messageService;
    @Autowired
    ModelService modelService;

    @Autowired
    CombinedEntityRepository combinedEntityRepository;
    @GetMapping("/admin/data/json")
    public List<CombinedEntity> getData() {

        return combinedEntityRepository.findAll();
    }
}
