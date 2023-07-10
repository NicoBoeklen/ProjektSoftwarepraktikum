package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Benutzer;
import com.example.projektsoftwarepraktikum.entity.CombinedEntity;
import com.example.projektsoftwarepraktikum.entity.NegotiationModel;
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

    @Autowired
    private ModelService modelService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CombinedEntityService combinedEntityService;

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
        //Rolle userRole = new Rolle("ROLE_USER");
        Rolle adminRole = new Rolle("ROLE_ADMIN");
        //roleService.saveRole(userRole);
        roleService.saveRole(adminRole);

        Set<Rolle> userRoles = new HashSet<>();
        //userRoles.add(userRole);

        Set<Rolle> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        //Später alle User bei Datenbank einlesen erstellt
        /*Benutzer normalUser = new Benutzer();
        normalUser.setUsername("user35");
        normalUser.setUserId(35);
        normalUser.setPassword(passwordEncoder.encode("1234"));
        normalUser.setRoles(userRoles);
        userService.saveUser(normalUser);
        */
        Benutzer admin = new Benutzer();
        admin.setUsername("admin");
        admin.setUserId(0);
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(adminRoles);
        userService.saveUser(admin);

        //Zieleingaben
        NegotiationModel negModel = new NegotiationModel();
        negModel.setUserId(35);
        negModel.setSelectedNegotiationID(17);
        negModel.setSelectedIssue("Training camp");
        negModel.setSelectedAspirationLevel(90);
        negModel.setSelectedReservationLevel(40);
        negModel.setSelectedTKIStyle("Avoiding");
        modelService.saveNegotiationModel(negModel);

        NegotiationModel negModel2 = new NegotiationModel();
        negModel2.setUserId(36);
        negModel2.setSelectedNegotiationID(17);
        negModel2.setSelectedIssue("Involvement of the fans");
        negModel2.setSelectedAspirationLevel(85);
        negModel2.setSelectedReservationLevel(45);
        negModel2.setSelectedTKIStyle("Compromising");
        modelService.saveNegotiationModel(negModel2);

        NegotiationModel negModel3 = new NegotiationModel();
        negModel3.setUserId(83);
        negModel3.setSelectedNegotiationID(41);
        negModel3.setSelectedIssue("Sponsoring");
        negModel3.setSelectedAspirationLevel(83);
        negModel3.setSelectedReservationLevel(32);
        negModel3.setSelectedTKIStyle("Competing");
        modelService.saveNegotiationModel(negModel3);

        NegotiationModel negModel4 = new NegotiationModel();
        negModel4.setUserId(84);
        negModel4.setSelectedNegotiationID(41);
        negModel4.setSelectedIssue("Lawn");
        negModel4.setSelectedAspirationLevel(95);
        negModel4.setSelectedReservationLevel(50);
        negModel4.setSelectedTKIStyle("Accommodating");
        modelService.saveNegotiationModel(negModel4);

        NegotiationModel negModel5 = new NegotiationModel();
        negModel5.setUserId(147);
        negModel5.setSelectedNegotiationID(73);
        negModel5.setSelectedIssue("Investment");
        negModel5.setSelectedAspirationLevel(62);
        negModel5.setSelectedReservationLevel(31);
        negModel5.setSelectedTKIStyle("Collaborating");
        modelService.saveNegotiationModel(negModel5);

        NegotiationModel negModel6 = new NegotiationModel();
        negModel6.setUserId(148);
        negModel6.setSelectedNegotiationID(73);
        negModel6.setSelectedIssue("Lawn");
        negModel6.setSelectedAspirationLevel(60);
        negModel6.setSelectedReservationLevel(38);
        negModel6.setSelectedTKIStyle("Collaborating");
        modelService.saveNegotiationModel(negModel6);

        NegotiationModel negModel7 = new NegotiationModel();
        negModel7.setUserId(168);
        negModel7.setSelectedNegotiationID(82);
        negModel7.setSelectedIssue("Sponsoring");
        negModel7.setSelectedAspirationLevel(65);
        negModel7.setSelectedReservationLevel(40);
        negModel7.setSelectedTKIStyle("Collaborating");
        modelService.saveNegotiationModel(negModel7);

        NegotiationModel negModel8 = new NegotiationModel();
        negModel8.setUserId(167);
        negModel8.setSelectedNegotiationID(82);
        negModel8.setSelectedIssue("Lawn");
        negModel8.setSelectedAspirationLevel(65);
        negModel8.setSelectedReservationLevel(40);
        negModel8.setSelectedTKIStyle("Competing");
        modelService.saveNegotiationModel(negModel8);

        NegotiationModel negModel9 = new NegotiationModel();
        negModel9.setUserId(193);
        negModel9.setSelectedNegotiationID(96);
        negModel9.setSelectedIssue("Lawn");
        negModel9.setSelectedAspirationLevel(68);
        negModel9.setSelectedReservationLevel(45);
        negModel9.setSelectedTKIStyle("Collaborating");
        modelService.saveNegotiationModel(negModel9);

        NegotiationModel negModel10 = new NegotiationModel();
        negModel10.setUserId(194);
        negModel10.setSelectedNegotiationID(96);
        negModel10.setSelectedIssue("Lawn");
        negModel10.setSelectedAspirationLevel(68);
        negModel10.setSelectedReservationLevel(45);
        negModel10.setSelectedTKIStyle("Collaborating");
        modelService.saveNegotiationModel(negModel10);

        NegotiationModel negModel11 = new NegotiationModel();
        negModel11.setUserId(235);
        negModel11.setSelectedNegotiationID(117);
        negModel11.setSelectedIssue("Training camp");
        negModel11.setSelectedAspirationLevel(86);
        negModel11.setSelectedReservationLevel(52);
        negModel11.setSelectedTKIStyle("Accommodating");
        modelService.saveNegotiationModel(negModel11);

        NegotiationModel negModel12 = new NegotiationModel();
        negModel12.setUserId(236);
        negModel12.setSelectedNegotiationID(117);
        negModel12.setSelectedIssue("Involvement of the fans");
        negModel12.setSelectedAspirationLevel(77);
        negModel12.setSelectedReservationLevel(55);
        negModel12.setSelectedTKIStyle("Collaborating");
        modelService.saveNegotiationModel(negModel12);


        //Admin-Entity
        //für jeden User
        CombinedEntity ce1 = new CombinedEntity(modelService.findNegotiationModelByUserId(35), messageService.getLastMessage(17));
        CombinedEntity ce2 = new CombinedEntity(modelService.findNegotiationModelByUserId(36), messageService.getLastMessage(17));
        CombinedEntity ce3 = new CombinedEntity(modelService.findNegotiationModelByUserId(83), messageService.getLastMessage(41));
        CombinedEntity ce4 = new CombinedEntity(modelService.findNegotiationModelByUserId(84), messageService.getLastMessage(41));
        CombinedEntity ce5 = new CombinedEntity(modelService.findNegotiationModelByUserId(147), messageService.getLastMessage(73));
        CombinedEntity ce6 = new CombinedEntity(modelService.findNegotiationModelByUserId(148), messageService.getLastMessage(73));
        CombinedEntity ce7 = new CombinedEntity(modelService.findNegotiationModelByUserId(193), messageService.getLastMessage(96));
        CombinedEntity ce8 = new CombinedEntity(modelService.findNegotiationModelByUserId(194), messageService.getLastMessage(96));
        CombinedEntity ce9 = new CombinedEntity(modelService.findNegotiationModelByUserId(167), messageService.getLastMessage(82));
        CombinedEntity ce10 = new CombinedEntity(modelService.findNegotiationModelByUserId(168), messageService.getLastMessage(82));
        CombinedEntity ce11 = new CombinedEntity(modelService.findNegotiationModelByUserId(235), messageService.getLastMessage(117));
        CombinedEntity ce12 = new CombinedEntity(modelService.findNegotiationModelByUserId(236), messageService.getLastMessage(117));

        combinedEntityService.saveCombinedEntity(ce1);
        combinedEntityService.saveCombinedEntity(ce2);
        combinedEntityService.saveCombinedEntity(ce3);
        combinedEntityService.saveCombinedEntity(ce4);
        combinedEntityService.saveCombinedEntity(ce5);
        combinedEntityService.saveCombinedEntity(ce6);
        combinedEntityService.saveCombinedEntity(ce7);
        combinedEntityService.saveCombinedEntity(ce8);
        combinedEntityService.saveCombinedEntity(ce9);
        combinedEntityService.saveCombinedEntity(ce10);
        combinedEntityService.saveCombinedEntity(ce11);
        combinedEntityService.saveCombinedEntity(ce12);



    }
}
