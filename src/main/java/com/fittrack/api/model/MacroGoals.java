package com.fittrack.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "macro_goals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MacroGoals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double protein;  // in grams
    private Double carbs;    // in grams
    private Double fat;      // in grams
    private Integer calories; // in kcal

    @OneToOne(mappedBy = "macroGoals")
    @JsonIgnore
    private User user;
}
