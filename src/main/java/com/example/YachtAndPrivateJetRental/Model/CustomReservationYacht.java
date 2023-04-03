package com.example.YachtAndPrivateJetRental.Model;

import com.example.YachtAndPrivateJetRental.Enums.RentalType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "custom_reservation_yacht")
public class CustomReservationYacht {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "yacht_id", referencedColumnName = "id",nullable = false)
    private  Yacht yacht;

    @ManyToOne
    @JoinColumn(name = "duration_id", referencedColumnName = "id",nullable = false)
    private  Duration duration;

    @Column(nullable = false)
    private String availability;

    @Column(nullable = false)
    private String demand;

    @Column(nullable = false)
    private RentalType type;

    @Column(nullable = false)
    private String season;

    @Column(nullable = false)
    private String bookingTime;

    @Column(nullable = false)
    private double price;
}
