package org.skypro.exam.service;

import org.skypro.exam.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    @Override
    public Question add(String question, String answer) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "Math questions cannot be added manually");
    }

    @Override
    public Question remove(String question, String answer) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "Math questions cannot be removed manually");
    }

    @Override
    public Collection<Question> getAll() {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "Math questions cannot be listed");
    }

    @Override
    public Question getRandomQuestion() {
        Random r = new Random();
        int a = r.nextInt(20) + 1;
        int b = r.nextInt(20) + 1;
        String[] ops = {"+", "-", "*", "/"};
        String op = ops[r.nextInt(ops.length)];

        int result;
        switch (op) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> {
                a = a * b;
                result = a / b;
            }
            default -> throw new IllegalStateException();
        }

        return new Question(a + " " + op + " " + b, String.valueOf(result));
    }
}
