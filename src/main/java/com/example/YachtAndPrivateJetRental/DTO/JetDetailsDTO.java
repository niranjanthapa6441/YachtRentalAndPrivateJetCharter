package com.example.YachtAndPrivateJetRental.DTO;

import com.example.YachtAndPrivateJetRental.Enums.FleetStatus;
import com.example.YachtAndPrivateJetRental.Model.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class JetDetailsDTO {

    private int id;
    private String manufacturer;
    private int capacity;
    private String manufacturedDate;
    private double maximumRange;

    private String name;
    private double altitude;

    private String category;

    private String owner;
    private double fuelConsumption;
    private double maximumSpeed;

    private FleetStatus fleetStatus;

    private String jetDescription;

    private List<String> imagePaths;

    private List<String> services;

    private List<String> amenities;

    private List<JetPriceDTO> pricing;
}
