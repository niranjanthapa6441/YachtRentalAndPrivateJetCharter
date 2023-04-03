package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class Service {
    @Id
    private String id;

    @Column(nullable = false,unique = true)
    private String service;
}
