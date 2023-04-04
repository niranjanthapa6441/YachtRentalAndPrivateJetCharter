package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "trip_type")
public class TripType {
    @SequenceGenerator(
            name = "trip_type_id_sequence",
            sequenceName = "trip_type_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="trip_type_sequence"
    )
    private String id;

    @Column(nullable = false,unique = true)
    private String tripType;
}
