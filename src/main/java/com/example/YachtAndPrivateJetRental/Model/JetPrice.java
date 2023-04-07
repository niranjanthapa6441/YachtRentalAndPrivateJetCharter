package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jet_price")
public class JetPrice {
    @SequenceGenerator(
            name = "jet_price_id_seq",
            sequenceName = "jet_price_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="jet_price_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id", nullable = false)
    private Jet jet;
    @ManyToOne
    @JoinColumn(name = "duration_id", referencedColumnName = "id", nullable = false)
    private Duration duration;
    @Column(nullable = false)
    private double price;

}
