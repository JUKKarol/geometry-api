package com.github.jukkarol.geometry_api.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ShapeType {
    SQUARE,
    RECTANGLE,
    CIRCLE;

    @JsonCreator
    public static ShapeType fromString(String value) {
        return ShapeType.valueOf(value.toUpperCase());
    }
}
