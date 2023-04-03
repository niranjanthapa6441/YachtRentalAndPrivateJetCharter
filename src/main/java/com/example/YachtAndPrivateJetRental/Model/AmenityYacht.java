package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "amenity_yacht")
public class AmenityYacht {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id",nullable = false)
    private  Yacht yacht;

    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id",nullable = false)
    private  Amenity amenity;
}
