package com.example.YachtAndPrivateJetRental.Model;

import com.example.YachtAndPrivateJetRental.Enums.FleetStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "jet")
public class Jet {
    @SequenceGenerator(
            name = "jet_id_seq",
            sequenceName = "jet_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="jet_sequence"
    )
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id", name = "manufacturer_id")
    private Manufacturer manufacturer;
    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false)
    private Date manufacturedDate;
    @Column(nullable = false)
    private double maximumRange;
    @Column(nullable = false)
    private double altitude;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id", nullable = false)
    private Owner owner;
    @Column(nullable = false)
    private double fuelConsumption;
    @Column(nullable = false)
    private double maximumSpeed;

    @Column(nullable = false)
    private FleetStatus fleetStatus;

    @Column(nullable = false, columnDefinition = "text")
    private String jetDescription;

}
