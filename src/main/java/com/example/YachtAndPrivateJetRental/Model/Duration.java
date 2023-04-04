package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Entity
@Data
@Table(name = "duration")
public class Duration {
    @SequenceGenerator(
            name = "duration_id_seq",
            sequenceName = "duration_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="duration_sequence"
    )
    private int id;

    @Column(nullable = false,unique = true)
    private String Duration;
}
