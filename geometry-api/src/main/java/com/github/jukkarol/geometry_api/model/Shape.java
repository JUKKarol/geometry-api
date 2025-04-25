package com.github.jukkarol.geometry_api.model;

import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shapes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shape_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ShapeType type;

    @OneToMany(mappedBy = "shape", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShapeParameter> parameters = new ArrayList<>();
}
