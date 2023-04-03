package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "owner_amenity")
public class OwnerAmenity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id", nullable = false)
    private Amenity Amenity;

    @Column(nullable = false)
    private double price;
}
