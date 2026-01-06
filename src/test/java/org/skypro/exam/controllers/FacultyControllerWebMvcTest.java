package org.skypro.exam.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.exam.model.Faculty;
import org.skypro.exam.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateFaculty() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor", "red", null);

        Mockito.when(facultyService.saveFaculty(Mockito.any()))
                .thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    void shouldGetFacultyById() throws Exception {
        Mockito.when(facultyService.getFacultyById(1L))
                .thenReturn(Optional.of(new Faculty(1L, "Gryffindor", "red", null)));

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("red"));
    }

    @Test
    void shouldReturn404IfFacultyNotFound() throws Exception {
        Mockito.when(facultyService.getFacultyById(99L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/faculty/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFilterFacultyByNameOrColor() throws Exception {
        Mockito.when(facultyService.findByNameOrColor("red"))
                .thenReturn(List.of(new Faculty(1L, "Gryffindor", "red", null)));

        mockMvc.perform(get("/faculty/search")
                        .param("value", "red"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
