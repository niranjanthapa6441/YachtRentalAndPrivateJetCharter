package com.example.YachtAndPrivateJetRental.Repository;


import com.example.YachtAndPrivateJetRental.Model.JetLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JetLocationRepo extends JpaRepository<JetLocation, Integer> {
}
