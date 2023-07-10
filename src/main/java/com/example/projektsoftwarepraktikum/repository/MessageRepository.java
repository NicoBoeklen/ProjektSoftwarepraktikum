package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<NegotiationMessage, Integer> {
    NegotiationMessage findByNegotiationMessageId(final Integer negotiationMessageId);

    //ursprünglich mit Union Werte in eine Spalte und alle mit Liste
    //JPA unterstützt kein UNION
    //@Query("SELECT AVG(nm.TKI_Accommodating_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Avoiding_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Collaborating_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Competing_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Compromising_Quantile_Rel) FROM Negotiation_Message nm")
    @Query("SELECT nm.senderId,avg(nm.TKI_Accommodating),avg(nm.TKI_Compromising), avg(nm.TKI_Compromising) FROM NegotiationMessage nm GROUP BY nm.negotiation,nm.senderId")
    List<List<Double>> getAverageValuesForEachUser();


    //USER QUERIES
    @Query("SELECT AVG(nm.TKI_Accommodating_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getAccommodating(@Param("filterValue") Integer filterValue, @Param("senderId") Integer senderId);

    @Query("SELECT AVG(nm.TKI_Avoiding_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getAvoiding(@Param("filterValue") Integer filterValue, @Param("senderId") Integer senderId);

    @Query("SELECT AVG(nm.TKI_Collaborating_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getCollaborating(@Param("filterValue") Integer filterValue, @Param("senderId") Integer senderId);

    @Query("SELECT AVG(nm.TKI_Competing_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getCompeting(@Param("filterValue") Integer filterValue, @Param("senderId") Integer senderId);

    @Query("SELECT AVG(nm.TKI_Compromising_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getCompromising(@Param("filterValue") Integer filterValue, @Param("senderId") Integer senderId);

    //#################################################
    //ADMIN QUERIES
    @Query("SELECT AVG(nm.TKI_Accommodating_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getAllUserAccommodating();

    @Query("SELECT AVG(nm.TKI_Avoiding_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getAllUserAvoiding();

    @Query("SELECT AVG(nm.TKI_Collaborating_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getAllUserCollaborating();

    @Query("SELECT AVG(nm.TKI_Competing_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getAllUserCompeting();

    @Query("SELECT AVG(nm.TKI_Compromising_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getAllUserCompromising();

    @Query("SELECT AVG(nm.jointUtilityBest) FROM NegotiationMessage nm WHERE nm.negoOutcome='ACCEPT'")
    Double jointUtilityAllUser();

    @Query("SELECT AVG(nm.contractImbalanceBest) FROM NegotiationMessage nm WHERE nm.negoOutcome='ACCEPT'")
    Double contractImbalanceAllUser();

    @Query("SELECT AVG(nm.messageCount) FROM NegotiationMessage nm WHERE nm.negoOutcome='ACCEPT'")
    Double messageCountAllUser();

    @Query("SELECT DISTINCT nm.negotiation.negotiationId FROM NegotiationMessage nm where nm.senderId= :filterValue")
    List<Integer> getNegotiationIdQuery(@Param("filterValue") Integer filterValue);

    @Query("SELECT DISTINCT nm.senderId FROM NegotiationMessage nm where nm.negotiation.negotiationId= :negotiation and NOT nm.senderId = :senderId")
    List<String> getPartnerQuery(@Param("negotiation") Integer negotiation, @Param("senderId") Integer senderId);

    @Query("SELECT nm.receiversBestCase FROM NegotiationMessage nm WHERE NOT nm.messageType='QUESTION' AND NOT nm.messageType='CLARIFICATION' AND NOT nm.messageType='INIT' AND nm.senderId=:partnerId AND nm.negotiation.negotiationId=:negotiation")
    List<Double> receiversBestCase(@Param("negotiation") Integer negotiation, @Param("partnerId") Integer partnerId);

    @Query("SELECT nm.receiversWorstCase FROM NegotiationMessage nm WHERE NOT nm.messageType='QUESTION' AND NOT nm.messageType='CLARIFICATION' AND NOT nm.messageType='INIT' AND nm.senderId=:partnerId AND nm.negotiation.negotiationId=:negotiation")
    List<Double> receiversWorstCase(@Param("negotiation") Integer negotiation, @Param("partnerId") Integer partnerId);

    @Query("SELECT nm.contractImbalanceBest FROM NegotiationMessage nm WHERE NOT nm.messageType='QUESTION' AND NOT nm.messageType='CLARIFICATION' AND NOT nm.messageType='INIT' AND nm.senderId=:partnerId AND nm.negotiation.negotiationId=:negotiation")
    List<Double> getContractImbalance(@Param("negotiation") Integer negotiation, @Param("partnerId") Integer partnerId);

    @Query("SELECT nm.jointUtilityBest FROM NegotiationMessage nm WHERE NOT nm.messageType='QUESTION' AND NOT nm.messageType='CLARIFICATION' AND NOT nm.messageType='INIT' AND nm.senderId=:userId AND nm.negotiation.negotiationId=:negotiation")
    List<Double> getJointUtilityBest(@Param("negotiation") Integer negotiation, @Param("userId") Integer userId);

    @Query("SELECT nm.negotiationMessageId FROM NegotiationMessage nm WHERE nm.messageType='ACCEPT' OR nm.messageType='REJECT' AND nm.negotiation.negotiationId=:negotiation")
    Integer getLastMessage(@Param("negotiation") Integer negotiation);
}