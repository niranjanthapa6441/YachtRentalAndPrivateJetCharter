package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Payment;
import com.example.YachtAndPrivateJetRental.Model.ReservationPackageYacht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationPackageYachtRepo extends JpaRepository<ReservationPackageYacht,String> {
}

