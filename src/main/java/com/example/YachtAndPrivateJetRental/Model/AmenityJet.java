package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "amenity_jet")
public class AmenityJet {
    @SequenceGenerator(
            name = "amenity_jet_id_seq",
            sequenceName = "amenity_jet_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="amenity_sequence"
    )
    private String id;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id",nullable = false)
    private  Jet jet;

    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id",nullable = false)
    private  Amenity amenity;


}
