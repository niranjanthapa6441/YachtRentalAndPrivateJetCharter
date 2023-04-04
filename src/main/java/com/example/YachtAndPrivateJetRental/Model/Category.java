package com.example.YachtAndPrivateJetRental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {
    @SequenceGenerator(
            name = "category_id_seq",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="category_sequence"
    )
    private int id;

    @Column(nullable = false,unique = true)
    private String category;

    @ManyToOne
    @JoinColumn(name = "transportation_type_id",nullable = false, referencedColumnName = "id")
    private Transportation type;
}
