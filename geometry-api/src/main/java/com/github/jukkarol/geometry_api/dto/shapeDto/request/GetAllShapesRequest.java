package com.github.jukkarol.geometry_api.dto.shapeDto.request;

import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllShapesRequest {
    private ShapeType type;
}
