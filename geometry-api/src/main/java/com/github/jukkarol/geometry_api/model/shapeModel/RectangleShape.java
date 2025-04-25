package com.github.jukkarol.geometry_api.model.shapeModel;

import com.github.jukkarol.geometry_api.model.Shape;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RECTANGLE")
public class RectangleShape extends Shape {

    public boolean isValid() {
        boolean hasA = getParameters().stream()
                .anyMatch(p -> p.getName().equals("a") && p.getValue() > 0);

        boolean hasB = getParameters().stream()
                .anyMatch(p -> p.getName().equals("b") && p.getValue() > 0);

        return hasA && hasB;
    }
}