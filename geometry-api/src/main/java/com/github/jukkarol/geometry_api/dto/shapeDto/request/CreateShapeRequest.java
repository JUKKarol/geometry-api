package com.github.jukkarol.geometry_api.dto.shapeDto.request;

import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateShapeRequest {

    @NotNull(message = "Shape type cannot be null")
    private ShapeType type;

    @NotEmpty(message = "Shape parameters cannot be empty")
    private List<ShapeParameter> parameters;
}
