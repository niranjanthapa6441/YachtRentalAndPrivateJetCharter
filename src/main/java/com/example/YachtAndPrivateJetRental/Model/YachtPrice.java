package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "yacht_price")
@Data
public class YachtPrice {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id", nullable = false)
    private Yacht yacht;

    @ManyToOne
    @JoinColumn(name = "duration_id", referencedColumnName = "id", nullable = false)
    private Duration duration;

    @Column(nullable = false)
    private double price;
}
