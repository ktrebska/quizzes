package pl.edu.wszib.quiz.service;

import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.ArrayAnswer;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;

import java.util.List;

public interface IQuizService {
    List<Quiz> getQuizzes();
    Quiz findById(Integer id);
    void addQuiz (Quiz quiz);
    Quiz findByName(String name);
    void updateQuiz (Quiz quiz);

    void createQuiz(Quiz quiz, Question question, ArrayAnswer array);

    int solve(Integer id, Answer ans);

    int countCorrectAnswers(Integer quizId);
}
