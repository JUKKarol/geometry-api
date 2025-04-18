package com.github.jukkarol.geometry_api.repository;

import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {
    List<Shape> findAllByType(ShapeType shapeType);
}
