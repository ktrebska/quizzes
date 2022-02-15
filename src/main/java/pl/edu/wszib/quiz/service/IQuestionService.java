package pl.edu.wszib.quiz.service;

import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;

import java.util.List;

public interface IQuestionService {
    List<Question> getQuestions();
    Question findById(Integer id);
    void addQuestion (Question question);
}
