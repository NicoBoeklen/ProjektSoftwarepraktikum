package com.example.projektsoftwarepraktikum.repository;

import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<NegotiationMessage, Integer> {
    NegotiationMessage findByNegotiationMessageId(final Integer negotiationMessageId);

    //ursprünglich mit Union Werte in eine Spalte und alle mit Liste
    //JPA unterstützt kein UNION
    //@Query("SELECT AVG(nm.TKI_Accommodating_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Avoiding_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Collaborating_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Competing_Quantile_Rel) FROM Negotiation_Message nm UNION ALL SELECT AVG(nm.TKI_Compromising_Quantile_Rel) FROM Negotiation_Message nm")
    @Query("SELECT AVG(nm.TKI_Accommodating_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getAccommodating();
    @Query("SELECT AVG(nm.TKI_Avoiding_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getAvoiding();
    @Query("SELECT AVG(nm.TKI_Collaborating_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getCollaborating();
    @Query("SELECT AVG(nm.TKI_Competing_Quantile_Rel) FROM NegotiationMessage nm")
    List<Double> getCompeting();
    @Query("SELECT AVG(nm.TKI_Compromising) FROM NegotiationMessage nm")
    List<Double> getCompromising();

}
