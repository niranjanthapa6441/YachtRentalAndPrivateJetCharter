package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "owner_amenity")
public class OwnerAmenity {
    @SequenceGenerator(
            name = "owner_amenity_id_sequence",
            sequenceName = "owner_amenity_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="owner_amenity_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id", nullable = false)
    private Amenity Amenity;

    @Column(nullable = false)
    private double price;
}
