package pl.edu.wszib.quiz.database;

import pl.edu.wszib.quiz.model.Quiz;

import java.util.List;

public interface IQuizDAO {
    List<Quiz> getQuizzes();
    Quiz getQuizById(int quizId);
    void addQuiz (Quiz quiz);

    Quiz getQuizByName(String name);
    void updateQuiz(Quiz quiz);
}
