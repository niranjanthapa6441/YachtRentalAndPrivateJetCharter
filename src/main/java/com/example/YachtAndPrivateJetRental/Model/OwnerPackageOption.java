package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "owner_package_option")
public class OwnerPackageOption {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "package_option_id", referencedColumnName = "id", nullable = false)
    private PackageOption packageOption;

}
