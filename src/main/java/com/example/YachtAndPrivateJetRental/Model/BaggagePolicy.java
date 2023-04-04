package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "baggage_policy")
public class BaggagePolicy {
    @SequenceGenerator(
            name = "baggage_policy_id_seq",
            sequenceName = "baggage_policy_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="baggage_policy_sequence"
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;

    @Column(nullable = false, columnDefinition = "text")
    private String policy;

    @Column(nullable = false)
    private int percentage;
}
