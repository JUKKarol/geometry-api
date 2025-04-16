package com.github.jukkarol.geometry_api.mapper;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.model.Shape;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShapeMapper {
    ShapeMapper INSTANCE = Mappers.getMapper(ShapeMapper.class);

    DisplayShapeDto shapeToDisplayShapeDto(Shape shape);

    List<DisplayShapeDto> shapesToDisplayShapeDtos(List<Shape> shapes);

    Shape createShapeRequestToShape(CreateShapeRequest createShapeRequest);
}
