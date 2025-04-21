package com.github.jukkarol.geometry_api.integration.shapeIntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jukkarol.geometry_api.dto.shapeDto.request.CreateShapeRequest;
import com.github.jukkarol.geometry_api.dto.shapeParameterDto.DisplayShapeParameterDto;
import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class POSTIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clearDb() {
        shapeRepository.deleteAll();
    }

    @Test
    void shouldCreateCircle_WhenValidRequest() throws Exception {
        CreateShapeRequest request = new CreateShapeRequest();
        request.setType(ShapeType.CIRCLE);
        DisplayShapeParameterDto param = new DisplayShapeParameterDto();
        param.setName("r");
        param.setValue(5.0);

        request.setParameters(List.of(param));

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        List<Shape> shapes = shapeRepository.findAll();
        assertThat(shapes).hasSize(1);
        assertThat(shapes.getFirst().getType()).isEqualTo(ShapeType.CIRCLE);
        assertThat(shapes.getFirst().getParameters().getFirst().getName()).isEqualTo("r");
    }

    @Test
    void shouldReturnBadRequest_WhenMissingParameter() throws Exception {
        CreateShapeRequest request = new CreateShapeRequest();
        request.setType(ShapeType.CIRCLE);
        request.setParameters(List.of());

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
