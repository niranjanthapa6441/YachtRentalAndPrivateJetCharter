package com.example.YachtAndPrivateJetRental.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "package_option")
public class PackageOption {
    @SequenceGenerator(
            name = "package_option_id_sequence",
            sequenceName = "package_option_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="package_option_sequence"
    )
    private String id;

    @Column(nullable = false,unique = true)
    private String option;
}
