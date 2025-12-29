package org.skypro.exam.controllers;

import org.skypro.exam.model.Faculty;
import org.skypro.exam.model.Student;
import org.skypro.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String testStudent() {
        return "StudentController is CURRENTLY WORKING";
    }

    @GetMapping("/age")
    public List<Student> getStudentsByAge(
            @RequestParam int min,
            @RequestParam int max
    ) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("/{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(Student::getFaculty)
                .orElse(null);
    }

}
