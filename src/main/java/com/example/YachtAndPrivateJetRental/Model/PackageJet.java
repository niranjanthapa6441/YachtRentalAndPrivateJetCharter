package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "package_jet")
public class PackageJet {
    @SequenceGenerator(
            name = "package_jet_sequence",
            sequenceName = "package_jet_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="package_jet_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id", nullable = false)
    private Jet jet;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private Package aPackage;
}
