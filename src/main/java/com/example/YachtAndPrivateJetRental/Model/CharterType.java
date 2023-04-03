package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "charter_type")
public class CharterType {
    @Id
    private String id;

    private String charterType;
}
