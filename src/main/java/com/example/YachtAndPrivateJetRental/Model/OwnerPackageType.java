package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OwnerPackageType")
public class OwnerPackageType {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "id", nullable = false)
    private PackageType packageType ;

}
