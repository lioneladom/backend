package com.fittrack.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String brand;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private String servingSize;
    private String photoUrl;
}
