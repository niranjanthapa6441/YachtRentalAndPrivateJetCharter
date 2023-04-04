package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @SequenceGenerator(
            name = "manufacturer_id_sequence",
            sequenceName = "manufacturer_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="manufacturer_sequence"
    )
    private int id;

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "transportation_type_id", nullable = false, referencedColumnName = "id")
    private Transportation type;
}
