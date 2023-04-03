package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "package_yacht")
public class PackageYacht {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id",nullable = false)
    private  Yacht yacht;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id",nullable = false)
    private  Package aPackage;

}
