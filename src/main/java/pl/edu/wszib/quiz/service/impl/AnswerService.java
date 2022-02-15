package pl.edu.wszib.quiz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.quiz.database.IAnswerDAO;
import pl.edu.wszib.quiz.database.IQuestionDAO;
import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;
import pl.edu.wszib.quiz.service.IAnswerService;

import java.util.List;

@Service
public class AnswerService implements IAnswerService {
    @Autowired
    IAnswerDAO answerDAO;

    public List<Answer> getAnswers() {
        return this.answerDAO.getAnswers();
    }
    public Answer findById(Integer id){
        return this.answerDAO.getAnswerById(id);
    }

    @Override
    public void addAnswer(Answer answer) {
        this.answerDAO.addAnswer(answer);
    }
}
