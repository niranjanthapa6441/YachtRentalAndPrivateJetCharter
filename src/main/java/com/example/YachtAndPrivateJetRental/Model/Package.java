package com.example.YachtAndPrivateJetRental.Model;

import com.example.YachtAndPrivateJetRental.Enums.PackageStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Entity
@Data
@Table(name = "package")
public class Package {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "duration_id", referencedColumnName = "id", nullable = false)
    private Duration duration;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(name = "package_description", nullable = false, columnDefinition = "text")
    private String packageDescription;

    @ManyToOne
    @JoinColumn(name = "package_option_id", referencedColumnName = "id", nullable = false)
    private PackageOption packageOption;
    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "id", nullable = false)
    private PackageType packageType;

    @ManyToOne
    @JoinColumn(name = "trip_type_id", referencedColumnName = "id", nullable = false)
    private TripType tripType;

    @Column(nullable = false)
    private PackageStatus status;
}
