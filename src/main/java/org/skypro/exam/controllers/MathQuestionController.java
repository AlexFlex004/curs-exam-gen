package org.skypro.exam.controllers;

import org.skypro.exam.model.Question;
import org.skypro.exam.service.MathQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    private final MathQuestionService service;

    public MathQuestionController(MathQuestionService service) {
        this.service = service;
    }


    @GetMapping("/add")
    public Question add(@RequestParam String question,
                        @RequestParam String answer) {
        return service.add(question, answer);
    }


    @GetMapping("/remove")
    public Question remove(@RequestParam String question,
                           @RequestParam String answer) {
        return service.remove(question, answer);
    }


    @GetMapping
    public Collection<Question> getAll() {
        return service.getAll();
    }


    @GetMapping("/random")
    public Question getRandom() {
        return service.getRandomQuestion();
    }
}
