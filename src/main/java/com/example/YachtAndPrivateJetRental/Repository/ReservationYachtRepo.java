package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Payment;
import com.example.YachtAndPrivateJetRental.Model.ReservationYacht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationYachtRepo extends JpaRepository<ReservationYacht,String> {
}

