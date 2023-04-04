package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "amenity_yacht")
public class AmenityYacht {
    @SequenceGenerator(
            name = "amenity_yacht_id_seq",
            sequenceName = "amenity_yacht_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="amenity_yacht_sequence"
    )
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id",nullable = false)
    private  Yacht yacht;

    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id",nullable = false)
    private  Amenity amenity;
}
