package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "image_package")
public class ImagePackage {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id",nullable = false)
    private  Package aPackage;

    @Column(nullable = false, unique = true)
    private String fileName;

    @Column(nullable = false, unique = true)
    private String imagePath;
}
