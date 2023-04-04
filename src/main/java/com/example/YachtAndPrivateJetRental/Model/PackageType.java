package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "package_type")
public class PackageType {
    @SequenceGenerator(
            name = "package_type_id_sequence",
            sequenceName = "package_type_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="package_type_sequence"
    )
    private int id;

    @Column(nullable = false,unique = true)
    private String type;
}
