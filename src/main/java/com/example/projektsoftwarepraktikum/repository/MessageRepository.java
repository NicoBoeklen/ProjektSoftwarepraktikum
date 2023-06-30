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
    @Query("SELECT AVG(nm.TKI_Accommodating_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getAccommodating(@Param("filterValue") Integer filterValue,@Param("senderId") Integer senderId);
    @Query("SELECT AVG(nm.TKI_Avoiding_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getAvoiding(@Param("filterValue") Integer filterValue,@Param("senderId") Integer senderId);
    @Query("SELECT AVG(nm.TKI_Collaborating_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getCollaborating(@Param("filterValue") Integer filterValue,@Param("senderId") Integer senderId);
    @Query("SELECT AVG(nm.TKI_Competing_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getCompeting(@Param("filterValue") Integer filterValue,@Param("senderId") Integer senderId);
    @Query("SELECT AVG(nm.TKI_Compromising_Quantile_Rel) FROM NegotiationMessage nm where nm.negotiation.negotiationId= :filterValue and nm.senderId= :senderId")
    List<Double> getCompromising(@Param("filterValue") Integer filterValue,@Param("senderId") Integer senderId);

}
