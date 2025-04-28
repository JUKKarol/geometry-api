package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.client.NotificationClient;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.mapper.ShapeParameterMapper;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.model.shapeModel.RectangleShape;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("circleStrategy")
@AllArgsConstructor
public class RectangleStrategy implements ShapeStrategy {
    private final ShapeRepository shapeRepository;
    public final ShapeParameterMapper shapeParameterMapper;
    private final NotificationClient notificationClient;

    @Override
    public boolean supports(ShapeType type) {
        return type == ShapeType.RECTANGLE;
    }

    @Override
    public void processAndSave(CreateShapeRequest dto) {
        List<ShapeParameter> parameters = shapeParameterMapper.displayShapeParameterDtosToShapeParameters(dto.getParameters());

        RectangleShape shape = new RectangleShape();

        parameters.forEach(p -> p.setShape(shape));
        shape.setParameters(parameters);
        shape.setType(ShapeType.RECTANGLE);

        if (!shape.isValid()) {
            throw new IllegalArgumentException("Rectangle must have a valid parameters: a and b");
        }

        shapeRepository.save(shape);

        notificationClient.sendNotification(shape.getId());
    }
}
