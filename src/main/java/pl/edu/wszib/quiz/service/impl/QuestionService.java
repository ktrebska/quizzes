package pl.edu.wszib.quiz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.quiz.database.IQuestionDAO;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;
import pl.edu.wszib.quiz.service.IQuestionService;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    IQuestionDAO questionDAO;

    public List<Question> getQuestions() {
        return this.questionDAO.getQuestions();
    }
    public Question findById(Integer id){
        return this.questionDAO.getQuestionById(id);
    }

    @Override
    public void addQuestion(Question question) {
        this.questionDAO.addQuestion(question);
    }
}
