package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.ServiceYacht;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceYachtRepo extends JpaRepository<ServiceYacht,Integer> {
}
