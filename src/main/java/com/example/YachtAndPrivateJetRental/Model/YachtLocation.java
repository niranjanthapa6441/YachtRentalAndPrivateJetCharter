package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "yacht_location")
@Data
public class YachtLocation {
    @SequenceGenerator(
            name = "yacht_location_id_sequence",
            sequenceName = "yacht_location",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="yacht_location_sequence"
    )
    private int id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String lat;
    @Column(nullable = false)
    private String lon;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id", nullable = false)
    private Yacht yacht;
}
