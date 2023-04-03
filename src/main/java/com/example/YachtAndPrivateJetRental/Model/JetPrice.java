package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jet_price")
public class JetPrice {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id", nullable = false)
    private Yacht yacht;
    @Column(nullable = false)
    private double distanceTravelled;
    @Column(nullable = false)
    private double price;

}
