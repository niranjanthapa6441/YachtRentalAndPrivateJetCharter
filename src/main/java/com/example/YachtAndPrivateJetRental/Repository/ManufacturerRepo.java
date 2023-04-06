package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepo extends JpaRepository<Manufacturer, Integer> {

    Optional<Manufacturer> findByName(String name);
}
