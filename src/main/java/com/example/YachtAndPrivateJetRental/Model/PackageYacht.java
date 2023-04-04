package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "package_yacht")
public class PackageYacht {
    @SequenceGenerator(
            name = "package_yacht_id_sequence",
            sequenceName = "package_yacht_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="package_yacht_sequence"
    )
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id",nullable = false)
    private  Yacht yacht;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id",nullable = false)
    private  Package aPackage;

}
