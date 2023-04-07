package com.example.YachtAndPrivateJetRental.Request;

import com.example.YachtAndPrivateJetRental.Enums.FleetStatus;
import com.example.YachtAndPrivateJetRental.Model.Category;
import com.example.YachtAndPrivateJetRental.Model.Owner;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Data
public class JetRequest {
    private String manufacturer;
    private String category;

    private String transportationType;

    private String name;
    private int capacity;
    private String manufacturedDate;
    private double maximumRange;
    private double altitude;
    private int ownerId;
    private double fuelConsumption;
    private double maximumSpeed;

    private String fleetStatus;

    private String jetDescription;

    List<String> amenities;

    List<String> services;

    List<JetPriceDurationRequest> jetPricing;

    private String address;

    private String lat;
    private String lon;

}
