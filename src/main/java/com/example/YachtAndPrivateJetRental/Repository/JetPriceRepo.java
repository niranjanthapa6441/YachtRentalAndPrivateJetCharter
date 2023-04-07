package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Jet;
import com.example.YachtAndPrivateJetRental.Model.JetPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JetPriceRepo extends JpaRepository<JetPrice, Integer> {
    List<JetPrice> findByJet(Jet jet);
}
