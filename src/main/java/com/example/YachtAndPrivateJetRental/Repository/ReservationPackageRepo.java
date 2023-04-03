package com.example.YachtAndPrivateJetRental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationPackageRepo extends JpaRepository<com.example.YachtAndPrivateJetRental.Model.ReservationPackage,String> {
}
