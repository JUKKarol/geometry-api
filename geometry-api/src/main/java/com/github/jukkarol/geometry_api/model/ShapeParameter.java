package com.github.jukkarol.geometry_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shape_parameters")
public class ShapeParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double value;

    @ManyToOne
    @JoinColumn(name = "shape_id", nullable = false)
    private Shape shape;
}