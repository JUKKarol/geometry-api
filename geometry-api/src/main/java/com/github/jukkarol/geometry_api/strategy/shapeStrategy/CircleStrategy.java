package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("circleStrategy")
@AllArgsConstructor
public class CircleStrategy implements ShapeStrategy {
    private final ShapeRepository shapeRepository;

    @Override
    public boolean supports(ShapeType type) {
        return type == ShapeType.CIRCLE;
    }

    @Override
    public void processAndSave(CreateShapeRequest dto) {
        ShapeParameter radius = dto.getParameters().stream()
                .filter(p -> "r".equals(p.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing parameter: r"));

        Shape shape = new Shape();
        shape.setType(ShapeType.CIRCLE);

        radius.setShape(shape);
        shape.getParameters().add(radius);

        shapeRepository.save(shape);
    }
}
