package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    private String id;

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "transportation_type_id", nullable = false, referencedColumnName = "id")
    private Transportation type;
}
