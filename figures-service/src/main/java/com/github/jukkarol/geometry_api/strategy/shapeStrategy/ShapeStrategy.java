package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;

public interface ShapeStrategy {
    boolean supports(ShapeType type);
    void processAndSave(CreateShapeRequest dto);
}
