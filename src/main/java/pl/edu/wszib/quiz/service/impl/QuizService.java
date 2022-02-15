package pl.edu.wszib.quiz.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.quiz.database.IQuizDAO;
import pl.edu.wszib.quiz.exceptions.LoginAlreadyUseException;
import pl.edu.wszib.quiz.model.*;
import pl.edu.wszib.quiz.model.view.RegisterUser;
import pl.edu.wszib.quiz.service.IAnswerService;
import pl.edu.wszib.quiz.service.IQuestionService;
import pl.edu.wszib.quiz.service.IQuizService;

import java.util.*;

@Service
public class QuizService implements IQuizService {

    @Autowired
    IQuizDAO quizDAO;

    @Autowired
    IAnswerService answerService;

    @Autowired
    IQuestionService questionService;

    public List<Quiz> getQuizzes() {
        return this.quizDAO.getQuizzes();
    }
    public Quiz findById(Integer id){
        return this.quizDAO.getQuizById(id);
    }

    public Quiz findByName(String name){
        return this.quizDAO.getQuizByName(name);
    }

    @Override
    public void addQuiz (Quiz quiz) {

        if(this.quizDAO.getQuizByName(quiz.getName()) == null){
            this.quizDAO.addQuiz(quiz);
        }

    }
    @Override
    public void updateQuiz (Quiz quiz) {

        this.quizDAO.updateQuiz(quiz);

    }

    @Override
    public void createQuiz(Quiz quiz, Question question, ArrayAnswer a1){
        Answer an1 = new Answer();
        Answer an2 = new Answer();
        Answer an3 = new Answer();
        Answer an4 = new Answer();

        an1.setAnswer(a1.getAnswer1());
        an2.setAnswer(a1.getAnswer2());
        an3.setAnswer(a1.getAnswer3());
        an4.setAnswer(a1.getAnswer4());

        an1.setCorrect(a1.isA1());
        an2.setCorrect(a1.isA2());
        an3.setCorrect(a1.isA3());
        an4.setCorrect(a1.isA4());

        this.answerService.addAnswer(an1);
        this.answerService.addAnswer(an2);
        this.answerService.addAnswer(an3);
        this.answerService.addAnswer(an4);

        Set<Answer> question1answers = new HashSet<>();

        question1answers.add(an1);
        question1answers.add(an2);
        question1answers.add(an3);
        question1answers.add(an4);


        question.setAnswers(question1answers);
        this.questionService.addQuestion(question);

        Set<Question> quiz1questions = new HashSet<>();


        quiz1questions.add(question);

        if(this.findByName(quiz.getName()) != null){
            quiz = this.findByName(quiz.getName());
            quiz.addQuestion(question);
            this.updateQuiz(quiz);
        }
        else{
            quiz.setQuestions(quiz1questions);
        }

        this.addQuiz(quiz);
    }

    @Override
    public int solve(Integer id, Answer ans){
        List<String> items = Arrays.asList(ans.getAnswer().split("\\s*,\\s*"));
        List<Integer> intList = new ArrayList<>();
        int points = 0;
        for(String s : items) intList.add(Integer.valueOf(s));

        for(int i: intList){
            if(this.answerService.findById(i).isCorrect()){
                points++;
            }else{
                points--;
            }
        }
        return points;
    }

    @Override
    public int countCorrectAnswers(Integer quizId){
        int correct = this.findById(quizId).getCorrectAnswersIds().toArray().length;
        return correct;
    }
}
