package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "amenity")
public class Amenity {
    @SequenceGenerator(
            name = "amenity_id_sequence",
            sequenceName = "amenity_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="amenity_sequence"
    )
    private String id;

    @Column(nullable = false)
    private String amenity;

}
