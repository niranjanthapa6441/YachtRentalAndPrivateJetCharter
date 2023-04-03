package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "package_itinerary")
@Data
public class PackageItinerary {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "sector_id", referencedColumnName = "id", nullable = false)
    private Sector sector;

    @Column(nullable = false,unique = true)
    private String imagePath;

    @Column(nullable = false,unique = true)
    private String fileName;

    @Column(nullable = false, columnDefinition ="text")
    private String description;
}
