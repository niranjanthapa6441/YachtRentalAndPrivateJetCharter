package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.PackageDescriptionYacht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDescriptionYachtRepo extends JpaRepository<PackageDescriptionYacht,String> {
}
