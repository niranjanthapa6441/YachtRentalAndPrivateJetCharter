package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transportation_type")
public class Transportation {
    @SequenceGenerator(
            name = "transportation_type_id_seq",
            sequenceName = "transportation_type_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="transportation_type_sequence"
    )
    private String id;

    private String type;
}
