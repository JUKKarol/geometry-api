package com.github.jukkarol.geometry_api.service;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.mapper.ShapeMapper;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShapeService {
    public final ShapeRepository shapeRepository;
    public final ShapeMapper shapeMapper;

    public List<DisplayShapeDto> GetShapesByType(ShapeType shapeType) {
        List<Shape> shapes = shapeRepository.findAllByType(shapeType);

        return shapeMapper.shapesToDisplayShapeDtos(shapes);
    }
}
