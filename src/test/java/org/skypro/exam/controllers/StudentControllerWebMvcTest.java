package org.skypro.exam.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.skypro.exam.model.Student;
import org.skypro.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    // CREATE
    @Test
    void shouldCreateStudent() throws Exception {
        Student student = new Student(1L, "Harry", 11, null);

        Mockito.when(studentService.saveStudent(Mockito.any()))
                .thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Harry"));
    }

    // READ ALL
    @Test
    void shouldGetAllStudents() throws Exception {
        Mockito.when(studentService.getAllStudents())
                .thenReturn(List.of(
                        new Student(1L, "Harry", 11, null),
                        new Student(2L, "Ron", 11, null)
                ));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    // READ BY ID
    @Test
    void shouldGetStudentById() throws Exception {
        Mockito.when(studentService.getStudentById(1L))
                .thenReturn(Optional.of(new Student(1L, "Harry", 11, null)));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Harry"));
    }

    // NEGATIVE 404
    @Test
    void shouldReturn404IfStudentNotFound() throws Exception {
        Mockito.when(studentService.getStudentById(99L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/students/99"))
                .andExpect(status().isNotFound());
    }

    // FILTER
    @Test
    void shouldFilterStudentsByAge() throws Exception {
        Mockito.when(studentService.getStudentsByAgeBetween(11, 18))
                .thenReturn(List.of(new Student(1L, "Harry", 11, null)));

        mockMvc.perform(get("/students/age")
                        .param("min", "11")
                        .param("max", "18"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}