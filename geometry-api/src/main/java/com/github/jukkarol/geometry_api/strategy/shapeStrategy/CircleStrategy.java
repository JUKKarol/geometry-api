package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.mapper.ShapeParameterMapper;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.model.shapeModel.CircleShape;
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
    private final ShapeParameterMapper shapeParameterMapper;

    @Override
    public boolean supports(ShapeType type) {
        return type == ShapeType.CIRCLE;
    }

    @Override
    public void processAndSave(CreateShapeRequest dto) {
        List<ShapeParameter> parameters = shapeParameterMapper.displayShapeParameterDtosToShapeParameters(dto.getParameters());

        CircleShape shape = new CircleShape();

        parameters.forEach(p -> p.setShape(shape));
        shape.setParameters(parameters);
        shape.setType(ShapeType.CIRCLE);

        if (!shape.isValid()) {
            throw new IllegalArgumentException("Circle must have a valid parameters: r");
        }

        shapeRepository.save(shape);
    }
}
