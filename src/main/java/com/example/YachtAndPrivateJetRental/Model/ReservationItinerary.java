package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation_itinerary")
@Data
public class ReservationItinerary {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(strategy = "uuid",name="system-uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id",nullable = false)
    private  Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "sector_id", referencedColumnName = "id",nullable = false)
    private  Sector sector;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

}
