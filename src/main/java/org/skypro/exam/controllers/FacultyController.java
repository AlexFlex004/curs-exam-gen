package org.skypro.exam.controllers;

import org.skypro.exam.model.Faculty;
import org.skypro.exam.model.Student;
import org.skypro.exam.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String testFaculty() {
        return "FacultyController is CURRENTLY WORKING";
    }

    @GetMapping("/search")
    public List<Faculty> search(@RequestParam String value) {
        return facultyService.findByNameOrColor(value);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudents(@PathVariable Long id) {
        return facultyService.getFacultyById(id)
                .map(Faculty::getStudents)
                .orElse(List.of());
    }

}
