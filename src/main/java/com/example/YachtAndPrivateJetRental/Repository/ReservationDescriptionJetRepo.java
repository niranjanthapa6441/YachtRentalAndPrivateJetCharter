package com.example.YachtAndPrivateJetRental.Repository;


import com.example.YachtAndPrivateJetRental.Model.ReservationDescriptionJet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDescriptionJetRepo extends JpaRepository<ReservationDescriptionJet,String> {
}

