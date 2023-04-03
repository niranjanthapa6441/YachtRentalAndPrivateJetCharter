package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.stereotype.Service;

@Entity
@Data
@Service
public class PackageAmenity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id", nullable = false)
    private Amenity amenity;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id", nullable = false)
    private Package aPackage;
}
