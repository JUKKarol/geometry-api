package com.github.jukkarol.geometry_api.controller;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.GetAllShapesRequest;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.service.ShapeService;
import com.github.jukkarol.geometry_api.strategy.shapeStrategy.ShapeStrategyManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RequestMapping("api/v1/shapes")
@RestController
@Validated
public class ShapesController {
    public final ShapeService shapeService;
    private final ShapeStrategyManager strategyManager;

    @PostMapping
    public ResponseEntity<String> addShape(@RequestBody @Valid CreateShapeRequest dto) {
        strategyManager.saveShape(dto);
        return ResponseEntity.ok("Shape saved: " + dto.getType());
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<DisplayShapeDto>> getShapesByType(@PathVariable @Valid String type) {
        ShapeType shapeType;
        shapeType = ShapeType.from(type);

        GetAllShapesRequest getAllShapesRequest = new GetAllShapesRequest(shapeType);
        List<DisplayShapeDto> shapes = shapeService.GetShapesByType(getAllShapesRequest.getType());

        if(shapes.isEmpty()) {
            throw new NoSuchElementException("No shapes found for type: " + shapeType);
        }

        return ResponseEntity.ok(shapes);
    }
}
