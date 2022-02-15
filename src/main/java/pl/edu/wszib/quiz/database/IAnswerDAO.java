package pl.edu.wszib.quiz.database;

import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;

import java.util.List;

public interface IAnswerDAO {
    List<Answer> getAnswers();
    Answer getAnswerById(int answerId);
    public void addAnswer(Answer answer);
}
