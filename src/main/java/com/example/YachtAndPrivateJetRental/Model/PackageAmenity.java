package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Service;

@Entity
@Data
@Service
public class PackageAmenity {
    @SequenceGenerator(
            name = "package_amenity_id_sequence",
            sequenceName = "package_amenity_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="package_amenity_sequence"
    )
    private String id;

    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id", nullable = false)
    private Amenity amenity;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private Package aPackage;
}
