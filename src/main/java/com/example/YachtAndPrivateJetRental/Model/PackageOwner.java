package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "package_owner")
public class PackageOwner {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private Package aPackage;
}
