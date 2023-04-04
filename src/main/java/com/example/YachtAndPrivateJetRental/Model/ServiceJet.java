package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service_jet")
public class ServiceJet {
    @SequenceGenerator(
            name = "service_jet_id_sequence",
            sequenceName = "service_jet_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="service_jet_sequence"
    )
    private String id;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id",nullable = false)
    private  Jet jet;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id",nullable = false)
    private Service service;
}
