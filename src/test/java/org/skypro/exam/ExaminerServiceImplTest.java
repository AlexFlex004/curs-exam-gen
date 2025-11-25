package org.skypro.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.exam.model.Question;
import org.skypro.exam.service.ExaminerServiceImpl;
import org.skypro.exam.service.QuestionService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExaminerServiceImplTest {

    private QuestionService javaService;
    private QuestionService mathService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        javaService = mock(QuestionService.class);
        mathService = mock(QuestionService.class);

        // Java сервис — возврат всех вопросов и случайного
        when(javaService.getAll()).thenReturn(List.of(
                new Question("Java Q1", "A1"),
                new Question("Java Q2", "A2")
        ));
        when(javaService.getRandomQuestion()).thenReturn(
                new Question("Java Q1", "A1"),
                new Question("Java Q2", "A2")
        );

        // Math сервис — getAll выбрасывает 405, getRandomQuestion возвращает случайный
        when(mathService.getAll()).thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED));
        when(mathService.getRandomQuestion()).thenReturn(
                new Question("2 + 2", "4"),
                new Question("3 * 5", "15")
        );

        // Передаем оба сервиса в ExaminerServiceImpl
        examinerService = new ExaminerServiceImpl(List.of(javaService, mathService));
    }

    @Test
    void getQuestions_returnsNQuestions() {
        Set<Question> questions = (Set<Question>) examinerService.getQuestions(3);
        assertEquals(3, questions.size());
    }

    @Test
    void getQuestions_tooMany_throwsBadRequest() {
        // Если запрашиваем больше, чем есть Java вопросов, и MathService не позволяет getAll → BAD_REQUEST
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(100));
    }
}
