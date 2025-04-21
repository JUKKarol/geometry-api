package com.github.jukkarol.geometry_api.mapper;

import com.github.jukkarol.geometry_api.dto.shapeParameterDto.DisplayShapeParameterDto;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShapeParameterMapper {
    ShapeMapper INSTANCE = Mappers.getMapper(ShapeMapper.class);

    DisplayShapeParameterDto shapeParameterToDisplayShapeParameterDto(ShapeParameter shapeParameter);

    List<ShapeParameter> displayShapeParameterDtosToShapeParameters(List<DisplayShapeParameterDto> displayShapeDto);
}
