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
public class SquareStrategy implements ShapeStrategy {
    private final ShapeRepository shapeRepository;

    @Override
    public boolean supports(ShapeType type) {
        return type == ShapeType.SQUARE;
    }

    @Override
    public void processAndSave(CreateShapeRequest dto) {
        ShapeParameter a = dto.getParameters().stream()
                .filter(p -> "a".equals(p.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing parameter: a"));

        Shape shape = new Shape();
        shape.setType(ShapeType.SQUARE);

        a.setShape(shape);
        shape.getParameters().add(a);

        shapeRepository.save(shape);
    }
}
