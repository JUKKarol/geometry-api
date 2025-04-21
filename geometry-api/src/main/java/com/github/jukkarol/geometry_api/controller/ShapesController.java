package com.github.jukkarol.geometry_api.controller;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.GetAllShapesRequest;
import com.github.jukkarol.geometry_api.service.ShapeService;
import com.github.jukkarol.geometry_api.strategy.shapeStrategy.ShapeStrategyManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/shapes")
@RestController
@Validated
public class ShapesController {
    public final ShapeService shapeService;
    private final ShapeStrategyManager strategyManager;

    @PostMapping
    public ResponseEntity<String> addShape(@RequestBody CreateShapeRequest dto) {
        strategyManager.saveShape(dto);
        return ResponseEntity.ok("Shape saved: " + dto.getType());
    }

    @GetMapping()
    public ResponseEntity<List<DisplayShapeDto>> getShapesByType(@RequestBody @Valid GetAllShapesRequest getAllShapesRequest) {
        List<DisplayShapeDto> shapes = shapeService.GetShapesByType(getAllShapesRequest.getType());

        return ResponseEntity.ok(shapes);
    }
}
