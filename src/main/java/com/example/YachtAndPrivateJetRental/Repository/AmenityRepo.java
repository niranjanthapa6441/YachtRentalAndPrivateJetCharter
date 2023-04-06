package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepo extends JpaRepository<Amenity,Integer> {
    Optional<Amenity> findByAmenity(String amenity);
}
