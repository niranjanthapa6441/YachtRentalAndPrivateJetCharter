package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OwnerPackageType")
public class OwnerPackageType {
    @SequenceGenerator(
            name = "owner_package_type_id_seq",
            sequenceName = "owner_package_type_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="owner_package_type_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "id", nullable = false)
    private PackageType packageType ;

}
