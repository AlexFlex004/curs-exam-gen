package org.skypro.exam.service;

import org.skypro.exam.model.Question;
import org.skypro.exam.service.ExaminerService;
import org.skypro.exam.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> services;
    private final Random random = new Random();

    public ExaminerServiceImpl(List<QuestionService> services) {
        this.services = services;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> result = new HashSet<>();

        int attempts = 0;
        int maxAttempts = amount * 10;

        while (result.size() < amount && attempts < maxAttempts) {
            QuestionService service = services.get(random.nextInt(services.size()));

            try {
                result.add(service.getRandomQuestion());
            } catch (ResponseStatusException e) {
                // если сервис "математики" запрещает операции — просто игнорируем
            }

            attempts++;
        }

        if (result.size() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not enough questions");
        }

        return result;
    }
}
