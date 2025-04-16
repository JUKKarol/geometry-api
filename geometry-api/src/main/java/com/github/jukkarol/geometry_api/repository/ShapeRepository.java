package com.github.jukkarol.geometry_api.repository;

import com.github.jukkarol.geometry_api.model.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {

}
