package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reservation_package")
public class ReservationPackage {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id",nullable = false)
    private  Package aPackage;
    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id",nullable = false)
    private  Reservation reservation;
}
