package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Service_Yacht")
public class ServiceYacht {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id",nullable = false)
    private  Yacht yacht;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id",nullable = false)
    private Service service;
}
