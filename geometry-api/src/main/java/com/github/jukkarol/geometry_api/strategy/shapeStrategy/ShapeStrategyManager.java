package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ShapeStrategyManager {
    private final List<ShapeStrategy> strategies;

    public void saveShape(CreateShapeRequest dto) {
        strategies.stream()
                .filter(s -> s.supports(dto.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported shape type: " + dto.getType()))
                .processAndSave(dto);
    }
}
