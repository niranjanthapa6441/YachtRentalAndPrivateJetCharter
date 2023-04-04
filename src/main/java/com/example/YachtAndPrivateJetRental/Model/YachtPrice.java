package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "yacht_price")
@Data
public class YachtPrice {
    @SequenceGenerator(
            name = "yacht_price_id_seq",
            sequenceName = "yacht_price_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="yacht_price_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id", nullable = false)
    private Yacht yacht;

    @ManyToOne
    @JoinColumn(name = "duration_id", referencedColumnName = "id", nullable = false)
    private Duration duration;

    @Column(nullable = false)
    private double price;
}
