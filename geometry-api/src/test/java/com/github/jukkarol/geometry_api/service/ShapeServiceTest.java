package com.github.jukkarol.geometry_api.service;

import com.github.jukkarol.geometry_api.dto.shapeDto.DisplayShapeDto;
import com.github.jukkarol.geometry_api.mapper.ShapeMapper;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.model.shapeModel.CircleShape;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShapeServiceTest {

    @Mock
    private ShapeRepository shapeRepository;

    @Mock
    private ShapeMapper shapeMapper;

    @InjectMocks
    private ShapeService shapeService;

    @Test
    void shouldReturnListOfDisplayShapeDto_WhenShapesExist() {
        CircleShape shape = new CircleShape();
        shape.setType(ShapeType.CIRCLE);

        DisplayShapeDto dto = new DisplayShapeDto();
        dto.setType(ShapeType.CIRCLE);

        when(shapeRepository.findAllByType(ShapeType.CIRCLE)).thenReturn(List.of(shape));
        when(shapeMapper.shapesToDisplayShapeDtos(List.of(shape))).thenReturn(List.of(dto));

        List<DisplayShapeDto> result = shapeService.GetShapesByType(ShapeType.CIRCLE);

        assertEquals(1, result.size());
        assertEquals(ShapeType.CIRCLE, result.getFirst().getType());

        verify(shapeRepository).findAllByType(ShapeType.CIRCLE);
        verify(shapeMapper).shapesToDisplayShapeDtos(List.of(shape));
    }

    @Test
    void shouldReturnEmptyList_WhenNoShapesFound() {
        when(shapeRepository.findAllByType(ShapeType.RECTANGLE)).thenReturn(Collections.emptyList());
        when(shapeMapper.shapesToDisplayShapeDtos(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<DisplayShapeDto> result = shapeService.GetShapesByType(ShapeType.RECTANGLE);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(shapeRepository).findAllByType(ShapeType.RECTANGLE);
        verify(shapeMapper).shapesToDisplayShapeDtos(Collections.emptyList());
    }
}
