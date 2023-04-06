package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportationRepo extends JpaRepository<Transportation, Integer> {
    Optional<Transportation> findByType(String transportationType);
}
