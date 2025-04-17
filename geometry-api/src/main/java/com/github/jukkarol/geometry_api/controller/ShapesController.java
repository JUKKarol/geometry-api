package com.github.jukkarol.geometry_api.controller;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.GetAllShapesRequest;
import com.github.jukkarol.geometry_api.service.ShapeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/shapes")
@RestController
public class ShapesController {
    public final ShapeService shapeService;

    @PostMapping()
    public ResponseEntity<DisplayShapeDto> register(@RequestBody CreateShapeRequest createShapeRequest) {
        DisplayShapeDto createdShape = shapeService.CreateShape(createShapeRequest);

        return ResponseEntity.ok(createdShape);
    }

    @GetMapping()
    public ResponseEntity<List<DisplayShapeDto>> register(@RequestBody GetAllShapesRequest getAllShapesRequest) {
        List<DisplayShapeDto> shapes = shapeService.GetShapesByType(getAllShapesRequest.getType());

        return ResponseEntity.ok(shapes);
    }
}
