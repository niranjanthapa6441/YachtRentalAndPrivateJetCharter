package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "payment")
public class Payment {
    @Id
    private String id;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String paymentPartner;

}
