package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sector")
public class Sector {
    @Id
    private String id;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String originLatitude;

    @Column(nullable = false)
    private String originLongitude;

    @Column(nullable = false)
    private String destinationLatitude;

    @Column(nullable = false)
    private String destinationLongitude;

    @Column(nullable = false)
    private double distance;
}
