package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.mapper.ShapeParameterMapper;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("circleStrategy")
@AllArgsConstructor
public class CircleStrategy implements ShapeStrategy {
    private final ShapeRepository shapeRepository;
    public final ShapeParameterMapper shapeParameterMapper;

    @Override
    public boolean supports(ShapeType type) {
        return type == ShapeType.CIRCLE;
    }

    @Override
    public void processAndSave(CreateShapeRequest dto) {
        List<ShapeParameter> parameters = shapeParameterMapper.displayShapeParameterDtosToShapeParameters(dto.getParameters());

        ShapeParameter radius = parameters.stream()
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
