package com.github.jukkarol.geometry_api.mapper;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.shapeModel.CircleShape;
import com.github.jukkarol.geometry_api.model.shapeModel.RectangleShape;
import com.github.jukkarol.geometry_api.model.shapeModel.SquareShape;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShapeMapper {
    ShapeMapper INSTANCE = Mappers.getMapper(ShapeMapper.class);

    DisplayShapeDto shapeToDisplayShapeDto(Shape shape);

    List<DisplayShapeDto> shapesToDisplayShapeDtos(List<Shape> shapes);

    CircleShape createCircleShapeFromRequest(CreateShapeRequest createShapeRequest);

    RectangleShape createRectangleShapeFromRequest(CreateShapeRequest createShapeRequest);

    SquareShape createSquareShapeFromRequest(CreateShapeRequest createShapeRequest);
}
