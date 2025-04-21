package com.github.jukkarol.geometry_api.integration.shapeIntegrationTests;

import com.github.jukkarol.geometry_api.model.Shape;
import com.github.jukkarol.geometry_api.model.ShapeParameter;
import com.github.jukkarol.geometry_api.model.enums.ShapeType;
import com.github.jukkarol.geometry_api.repository.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GETIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShapeRepository shapeRepository;

    @BeforeEach
    void setUp() {
        shapeRepository.deleteAll();
    }

    @Test
    void shouldReturnShapesOfGivenType() throws Exception {
        Shape shape = new Shape();
        shape.setType(ShapeType.CIRCLE);

        ShapeParameter param = new ShapeParameter();
        param.setName("r");
        param.setValue(5.0);
        param.setShape(shape);

        shape.setParameters(List.of(param));
        shapeRepository.save(shape);

        var result = mockMvc.perform(get("/api/v1/shapes/circle"))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = result.getResponse().getContentAsString();
        assertThat(jsonResponse).contains("CIRCLE");
        assertThat(jsonResponse).contains("r");
        assertThat(jsonResponse).contains("5.0");
    }

    @Test
    void shouldReturnEmptyList_WhenNoShapesOfType() throws Exception {
        var result = mockMvc.perform(get("/api/v1/shapes/circle"))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = result.getResponse().getContentAsString();
        assertThat(jsonResponse).isEqualTo("[]");
    }
}
