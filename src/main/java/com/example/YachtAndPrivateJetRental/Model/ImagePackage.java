package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "image_package")
public class ImagePackage {
    @SequenceGenerator(
            name = "image_package_id_seq",
            sequenceName = "image_package_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="image_package_sequence"
    )
    private String id;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id",nullable = false)
    private  Package aPackage;

    @Column(nullable = false, unique = true)
    private String fileName;

    @Column(nullable = false, unique = true)
    private String imagePath;
}
