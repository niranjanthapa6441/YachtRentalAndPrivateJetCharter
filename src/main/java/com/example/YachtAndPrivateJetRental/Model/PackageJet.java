package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "package_jet")
public class PackageJet {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id", nullable = false)
    private Jet jet;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private Package aPackage;
}
