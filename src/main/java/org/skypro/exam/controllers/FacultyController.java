package org.skypro.exam.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @GetMapping
    public String testFaculty() {
        return "FacultyController is CURRENTLY WORKING";
    }

}
