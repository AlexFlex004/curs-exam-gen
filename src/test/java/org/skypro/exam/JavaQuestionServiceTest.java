package org.skypro.exam;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.exam.model.Question;
import org.skypro.exam.service.JavaQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService();
    }

    @Test
    void addAndGetAllAndRemove() {
        Question q = new Question("Test question", "Test answer");

        Question added = service.add(q.getQuestion(), q.getAnswer());

        Question removed = service.remove(q.getQuestion(), q.getAnswer());

        Collection<Question> all = service.getAll();
        assertTrue(all.contains(q));




        all = service.getAll();
        assertFalse(all.contains(q));
    }

    @Test
    void getRandomQuestion_notEmpty() {

        Question random = service.getRandomQuestion();
        assertNotNull(random);
        assertNotNull(random.getQuestion());
        assertNotNull(random.getAnswer());
    }
}
