package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_role")
public class Role {
    @SequenceGenerator(
            name = "user_role_id_sequence",
            sequenceName = "user_role_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="user_role_sequence"
    )
    private String id;
}
