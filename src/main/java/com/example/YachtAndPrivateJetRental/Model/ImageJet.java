package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "image_jet")
public class ImageJet {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id",nullable = false)
    private  Jet jet;

    @Column(nullable = false, unique = true)
    private String fileName;

    @Column(nullable = false, unique = true)
    private String imagePath;
}
