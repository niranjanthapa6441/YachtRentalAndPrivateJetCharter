package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "yacht_location")
@Data
public class YachtLocation {
    @Id
    private String id;
}
