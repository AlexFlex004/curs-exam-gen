package org.skypro.exam.controllers;

import org.junit.jupiter.api.Test;
import org.skypro.exam.model.Faculty;
import org.skypro.exam.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/students";
    }

    // CREATE
    @Test
    void shouldCreateStudent() {
        Faculty gryffindor = new Faculty(null, "Gryffindor", "red", null);
        Student student = new Student(null, "Harry Potter", 11, gryffindor);

        ResponseEntity<Student> response =
                restTemplate.postForEntity(baseUrl(), student, Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
    }

    // READ
    @Test
    void shouldGetStudentById() {
        Student created = restTemplate

                .postForEntity(baseUrl(), new Student(null, "Ronald Weasley", 11, null), Student.class)
                .getBody();

        ResponseEntity<Student> response =
                restTemplate.getForEntity(
                        baseUrl() + "/" + created.getId(), Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Petr");
    }

    // UPDATE
    @Test
    void shouldUpdateStudent() {
        Student created = restTemplate
                .postForEntity(baseUrl(), new Student(null, "Hermione Granger", 11, null), Student.class)
                .getBody();

        created.setAge(19);
        restTemplate.put(baseUrl() + "/" + created.getId(), created);

        Student updated = restTemplate.getForObject(
                baseUrl() + "/" + created.getId(), Student.class);

        assertThat(updated.getAge()).isEqualTo(19);
    }

    // DELETE
    @Test
    void shouldDeleteStudent() {
        Student created = restTemplate
                .postForEntity(baseUrl(), new Student(null, "Draco Malfoy", 11, null), Student.class)
                .getBody();

        restTemplate.delete(baseUrl() + "/" + created.getId());

        ResponseEntity<Student> response =
                restTemplate.getForEntity(
                        baseUrl() + "/" + created.getId(), Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    // FILTER
    @Test
    void shouldFilterStudentsByAge() {
        ResponseEntity<Student[]> response =
                restTemplate.getForEntity(
                        baseUrl() + "?age=11", Student[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    // NEGATIVE CASE
    @Test
    void shouldReturn404WhenStudentNotFound() {
        ResponseEntity<Student> response =
                restTemplate.getForEntity(baseUrl() + "/999999", Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
