package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "owner_package_option")
public class OwnerPackageOption {
    @SequenceGenerator(
            name = "owner_package_option_id_seq",
            sequenceName = "owner_package_option_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="owner_package_option_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "package_option_id", referencedColumnName = "id", nullable = false)
    private PackageOption packageOption;

}
