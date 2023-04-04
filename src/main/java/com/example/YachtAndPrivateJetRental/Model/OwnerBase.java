package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "owner_base")
public class OwnerBase {
    @SequenceGenerator(
            name = "owner_base_id_seq",
            sequenceName = "owner_base_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="owner_base_sequence"
    )
    private String id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String lat;
    @Column(nullable = false)
    private String lon;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
}
