package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sector")
public class Sector {
    @SequenceGenerator(
            name = "sector_id_sequence",
            sequenceName = "sector_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="sector_sequence"
    )
    private int id;

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
