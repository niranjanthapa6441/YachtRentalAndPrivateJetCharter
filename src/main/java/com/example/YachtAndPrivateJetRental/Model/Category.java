package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    private String id;

    @Column(nullable = false,unique = true)
    private String category;

    @ManyToOne
    @JoinColumn(name = "transportation_type_id",nullable = false, referencedColumnName = "id")
    private Transportation type;
}
