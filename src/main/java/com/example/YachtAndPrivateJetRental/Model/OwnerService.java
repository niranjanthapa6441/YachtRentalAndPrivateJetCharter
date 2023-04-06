package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "owner_service")
public class OwnerService {
    @SequenceGenerator(
            name = "owner_service_id_seq",
            sequenceName = "owner_service_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="owner_service_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    private Services services;

    @Column(nullable = false)
    private double price;
}
