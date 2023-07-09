package com.example.projektsoftwarepraktikum.controller;

import com.example.projektsoftwarepraktikum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    MessageService messageService;
    @GetMapping("/admin/home")
    public String showAdmin(Model model) {

        List<Double> tkiList= messageService.averageTkiStyleAllUser();
        Double[] tkiAllUserValues = tkiList.toArray(new Double[0]);
        int[] intTKIArray = Arrays.stream(tkiAllUserValues)
                .mapToInt(value -> (int) Math.round(value))
                .toArray();
        model.addAttribute("tkiValuesInt", intTKIArray);
        String[] tkiTypes = new String[]{"Competing", "Compromising", "Collaborating", "Avoiding", "Accommodating"};
        model.addAttribute("tkiTypes", tkiTypes);
        //System.out.println(Arrays.toString(tkiAllUserValues));
        model.addAttribute("tkiAllUserValues", tkiAllUserValues);

    return "adminpage";
    }
}
