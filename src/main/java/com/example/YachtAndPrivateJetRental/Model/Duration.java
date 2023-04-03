package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Entity
@Data
@Table(name = "duration")
public class Duration {
    @Id
    private String id;

    @Column(nullable = false,unique = true)
    private String Duration;
}
