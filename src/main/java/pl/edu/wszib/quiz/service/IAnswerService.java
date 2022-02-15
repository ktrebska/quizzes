package pl.edu.wszib.quiz.service;

import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.Quiz;

import java.util.List;

public interface IAnswerService {
    List<Answer> getAnswers();
    Answer findById(Integer id);
    void addAnswer(Answer answer);
}
