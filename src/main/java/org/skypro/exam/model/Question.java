package org.skypro.exam.model;

import java.util.Objects;

public class Question {
    private String question;
    private String answer;

    public Question (String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question q = (Question) o;
        return Objects.equals(question, q.question) &&
                Objects.equals(answer, q.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        return question + " -- " + answer;
    }
}
