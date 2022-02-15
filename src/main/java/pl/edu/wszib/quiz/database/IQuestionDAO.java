package pl.edu.wszib.quiz.database;


import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;

import java.util.List;


public interface IQuestionDAO {
     List<Question> getQuestions();
     Question getQuestionById(int questionId);
     void addQuestion(Question question);
}
