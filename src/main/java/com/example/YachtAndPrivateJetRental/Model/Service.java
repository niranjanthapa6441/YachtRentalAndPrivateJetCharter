package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Service {
    @SequenceGenerator(
            name = "service_id_sequence",
            sequenceName = "service_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="service_sequence"
    )
    private String id;

    @Column(nullable = false,unique = true)
    private String service;
}
