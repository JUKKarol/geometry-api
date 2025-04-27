package com.github.jukkarol.geometry_api.strategy.shapeStrategy;

import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.dto.shapeParameterDto.DisplayShapeParameterDto;
import com.github.jukkarol.geometry_api.mapper.ShapeParameterMapper;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CircleStrategyTest {

    @Mock
    private ShapeRepository shapeRepository;

    @Mock
    private ShapeParameterMapper shapeParameterMapper;

    @InjectMocks
    private CircleStrategy circleStrategy;

    private CreateShapeRequest validRequest;

    @BeforeEach
    void setUp() {
        DisplayShapeParameterDto rDto = new DisplayShapeParameterDto("r", 5.0);
        validRequest = new CreateShapeRequest();
        validRequest.setType(ShapeType.CIRCLE);
        validRequest.setParameters(List.of(rDto));

        ShapeParameter radius = new ShapeParameter();
        radius.setName("r");
        radius.setValue(5.0);

        when(shapeParameterMapper.displayShapeParameterDtosToShapeParameters(any()))
                .thenReturn(List.of(radius));
    }

    @Test
    void shouldSaveCircleShape_WhenValidRequest() {
        circleStrategy.processAndSave(validRequest);

        ArgumentCaptor<Shape> shapeCaptor = ArgumentCaptor.forClass(Shape.class);
        verify(shapeRepository, times(1)).save(shapeCaptor.capture());

        Shape savedShape = shapeCaptor.getValue();
        assertEquals(ShapeType.CIRCLE, savedShape.getType());
        assertEquals(1, savedShape.getParameters().size());
        assertEquals("r", savedShape.getParameters().getFirst().getName());
        assertEquals(5.0, savedShape.getParameters().getFirst().getValue());
    }

    @Test
    void shouldThrowException_WhenRMissing() {
        validRequest.setParameters(List.of());
        when(shapeParameterMapper.displayShapeParameterDtosToShapeParameters(any()))
                .thenReturn(List.of());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                circleStrategy.processAndSave(validRequest)
        );

        assertEquals("Circle must have a valid parameters: r", exception.getMessage());
        verify(shapeRepository, never()).save(any());
    }
}
