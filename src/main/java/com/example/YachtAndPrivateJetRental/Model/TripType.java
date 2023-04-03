package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "trip_type")
public class TripType {
    @Id
    private String id;

    @Column(nullable = false,unique = true)
    private String tripType;
}
