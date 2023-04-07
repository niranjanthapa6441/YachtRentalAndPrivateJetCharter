package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Jet;
import com.example.YachtAndPrivateJetRental.Model.ServiceJet;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceJetRepo extends JpaRepository<ServiceJet,Integer> {

    List<ServiceJet> findByJet(Jet jet);
}
