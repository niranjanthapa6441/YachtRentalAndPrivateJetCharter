package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "custom_reservation_jet")
public class CustomReservationJet {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(strategy = "uuid",name="system-uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "jet_id", referencedColumnName = "id",nullable = false)
    private  Jet jet;

    @ManyToOne
    @JoinColumn(name = "duration_id", referencedColumnName = "id",nullable = false)
    private  Duration duration;

    @Column(nullable = false)
    private String availability;

    @Column(nullable = false)
    private String demand;

    @ManyToOne
    @JoinColumn(name = "charter_type_id", referencedColumnName = "id",nullable = false)
    private  CharterType type;

    @Column(nullable = false)
    private String season;

    @Column(nullable = false)
    private String bookingTime;
    @Column(nullable = false)
    private String emptyLeg;

    @Column(nullable = false)
    private double price;

}
