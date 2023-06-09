package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Benutzer;
import com.example.projektsoftwarepraktikum.entity.Rolle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private NegotiationService negotiationService;

    /**
     * Diese Methode wird zum Aufsetzen von Testdaten für die Datenbank verwendet werden. Die Methode wird immer dann
     * ausgeführt, wenn der Spring Kontext initialisiert wurde, d.h. wenn Sie Ihren Server (neu-)starten.
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Initialisiere Datenbank mit Testdaten...");
        //save all data from CSV File
        negotiationService.saveNegotiationData();

        //Später im NegotiationService bei jeder neuen SenderID passenden User erstellen
        // Initialisieren Sie Beispielobjekte und speichern Sie diese über Ihre Services
        Rolle userRole = new Rolle("ROLE_USER");
        Rolle adminRole = new Rolle("ROLE_ADMIN");
        roleService.saveRole(userRole);
        roleService.saveRole(adminRole);

        Set<Rolle> userRoles = new HashSet<>();
        userRoles.add(userRole);

        Set<Rolle> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        //Später alle User bei Datenbank einlesen erstellt
        Benutzer normalUser = new Benutzer();
        normalUser.setUsername("user");
        normalUser.setUserId(35);
        normalUser.setPassword(passwordEncoder.encode("1234"));
        normalUser.setRoles(userRoles);
        userService.saveUser(normalUser);

        Benutzer admin = new Benutzer();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(adminRoles);
        userService.saveUser(admin);
    }
}
