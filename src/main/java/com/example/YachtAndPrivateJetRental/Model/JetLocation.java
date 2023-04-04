package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jet_location")
public class JetLocation {
    @SequenceGenerator(
            name = "jet_location_id_seq",
            sequenceName = "jet_location_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="jet_location_sequence"
    )
    private int id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String lat;
    @Column(nullable = false)
    private String lon;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id", nullable = false)
    private Jet jet;
}
