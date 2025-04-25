package com.github.jukkarol.geometry_api.model.shapeModel;

import com.github.jukkarol.geometry_api.model.Shape;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CIRCLE")
public class CircleShape extends Shape {

    public boolean isValid() {
        return getParameters().stream()
                .anyMatch(p -> p.getName().equals("r") && p.getValue() > 0);
    }
}
