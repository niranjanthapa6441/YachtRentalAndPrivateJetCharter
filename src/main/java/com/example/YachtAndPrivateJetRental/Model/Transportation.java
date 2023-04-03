package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "transportation_type")
public class Transportation {
    @Id
    private String id;

    private String type;
}
