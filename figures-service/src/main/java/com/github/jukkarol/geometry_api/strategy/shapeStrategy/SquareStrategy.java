package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.client.NotificationClient;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.mapper.ShapeParameterMapper;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.model.shapeModel.SquareShape;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("circleStrategy")
@AllArgsConstructor
public class SquareStrategy implements ShapeStrategy {
    private final ShapeRepository shapeRepository;
    private final ShapeParameterMapper shapeParameterMapper;
    private final NotificationClient notificationClient;

    @Override
    public boolean supports(ShapeType type) {
        return type == ShapeType.SQUARE;
    }

    @Override
    public void processAndSave(CreateShapeRequest dto) {
        List<ShapeParameter> parameters = shapeParameterMapper.displayShapeParameterDtosToShapeParameters(dto.getParameters());

        SquareShape shape = new SquareShape();
        shape.setParameters(parameters);
        shape.setType(ShapeType.SQUARE);

        if (!shape.isValid()) {
            throw new IllegalArgumentException("Square must have a valid parameters: a");
        }

        shapeRepository.save(shape);

        notificationClient.sendNotification(shape.getId());
    }
}
