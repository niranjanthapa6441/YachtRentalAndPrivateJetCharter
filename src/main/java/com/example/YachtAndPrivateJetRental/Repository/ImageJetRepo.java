package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.ImageJet;
import com.example.YachtAndPrivateJetRental.Model.Jet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageJetRepo extends JpaRepository<ImageJet,Integer> {
    List<ImageJet> findByJet(Jet jet);
}
