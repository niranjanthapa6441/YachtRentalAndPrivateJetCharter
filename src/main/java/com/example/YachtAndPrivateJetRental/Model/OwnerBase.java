package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "owner_base")
public class OwnerBase {
    @Id
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
