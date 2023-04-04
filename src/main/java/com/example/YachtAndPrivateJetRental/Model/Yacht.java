package com.example.YachtAndPrivateJetRental.Model;

import com.example.YachtAndPrivateJetRental.Enums.FleetStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "yacht")
public class Yacht {
    @SequenceGenerator(
            name = "yacht_id_sequence",
            sequenceName = "yacht_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="yacht_sequence"
    )
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id",referencedColumnName = "id",nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id",nullable = false)
    private Category category;

    @Column(nullable = false)
    private double maximumRange;

    @Column(nullable = false)
    private double fuelConsumption;

    @Column(nullable = false)
    private double maxSpeed;

    @Column(nullable = false)
    private double length;

    @Column(nullable = false)
    private FleetStatus status;
}
