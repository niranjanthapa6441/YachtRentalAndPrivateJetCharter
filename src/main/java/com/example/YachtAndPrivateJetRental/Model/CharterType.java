package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "charter_type")
public class CharterType {
    @SequenceGenerator(
            name = "charter_type_id_seq",
            sequenceName = "charter_type_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="charter_type_sequence"
    )
    private int id;

    private String charterType;
}
