package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cancellation_policy")
public class CancellationPolicy {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;

    @Column(nullable = false, columnDefinition = "text")
    private String policy;

    @Column(nullable = false)
    private int percentage;

}
