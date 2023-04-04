package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Service_Yacht")
public class ServiceYacht {
    @SequenceGenerator(
            name = "service_yacht_id_sequence",
            sequenceName = "service_yacht_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="service_yacht_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id",nullable = false)
    private  Yacht yacht;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id",nullable = false)
    private Service service;
}
