package com.github.jukkarol.geometry_api.dto.shapeDto;

import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisplayShapeDto {
    private ShapeType type;

    private List<ShapeParameter> parameters;
}
