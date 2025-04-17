package com.github.jukkarol.geometry_api.controller;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.GetAllShapesRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/shapes")
@RestController
public class ShapesController {
    @PostMapping("/signup")
    public ResponseEntity<CreateShapeRequest> register(@RequestBody CreateShapeRequest createShapeRequest) {
        CreateShapeRequest createdShape =

        return ResponseEntity.ok(createdShape);
    }

    @GetMapping("/signup")
    public ResponseEntity<List<DisplayShapeDto>> register(@RequestBody GetAllShapesRequest getAllShapesRequest) {
        List<DisplayShapeDto> shapes =

        return ResponseEntity.ok(shapes);
    }
}
