package com.example.projektsoftwarepraktikum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Controller für alle Requests, die lediglich statische HTML-Inhalte darstellen und keine
 * ModelAttribute verwenden.
 */
@Controller
public class StaticPagesController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/aboutus").setViewName("aboutus");
        registry.addViewController("/register").setViewName("register");
    }
}