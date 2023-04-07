package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "package_jet_price")
@Data
public class PackageJetPrice {
    @SequenceGenerator(
            name = "package_id_sequence",
            sequenceName = "package_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="package_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "package_option_id", referencedColumnName = "id", nullable = false)
    private PackageOption packageOption;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id", nullable = false)
    private Jet jet;

    @Column(nullable = false)
    private double price;

}
