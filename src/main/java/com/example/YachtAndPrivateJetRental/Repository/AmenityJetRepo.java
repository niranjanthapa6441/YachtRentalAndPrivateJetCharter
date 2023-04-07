package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.AmenityJet;
import com.example.YachtAndPrivateJetRental.Model.Jet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityJetRepo extends JpaRepository<AmenityJet,Integer> {
    List<AmenityJet> findByJet(Jet jet);
}
