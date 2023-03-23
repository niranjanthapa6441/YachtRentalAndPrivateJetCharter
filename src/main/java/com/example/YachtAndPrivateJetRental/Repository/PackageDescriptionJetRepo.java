package com.example.YachtAndPrivateJetRental.Repository;


import com.example.YachtAndPrivateJetRental.Model.PackageDescriptionJet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDescriptionJetRepo extends JpaRepository<PackageDescriptionJet,String> {
}
