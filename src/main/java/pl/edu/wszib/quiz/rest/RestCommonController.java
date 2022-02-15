package pl.edu.wszib.quiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;
import pl.edu.wszib.quiz.service.IAnswerService;
import pl.edu.wszib.quiz.service.IQuestionService;
import pl.edu.wszib.quiz.service.IQuizService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RestCommonController {

    @Autowired
    IAnswerService answerService;

    @Autowired
    IQuizService quizService;

    @Autowired
    IQuestionService questionService;


    @RequestMapping(value = "/answer/{id}", method = RequestMethod.GET)
    public Answer getAnswer(@PathVariable int id) {
        Answer answer = answerService.findById(id);
        return answer;
    }

    @RequestMapping(value = "/answers", method = RequestMethod.GET)
    public List<Answer> getListOfAnswers(){
        List<Answer> answers = this.answerService.getAnswers();
        return answers;
    }

    @RequestMapping(value = "/quizes", method = RequestMethod.GET)
    public List<Quiz> getListOfQuizes(){
        List<Quiz> quizes = this.quizService.getQuizzes();
        return quizes;
    }

    @RequestMapping(value = "/mock", method = RequestMethod.GET)
    public void mock() {

        Answer a1 = new Answer( 1 , " odpowiedz1", true);
        Answer a2 = new Answer( 2 , " odpowiedz2", false);
        Answer a3 = new Answer( 3 , " odpowiedz3", false);
        Answer a4 = new Answer( 4 , " odpowiedz4", false);

        Set<Answer> question1answers = new HashSet<>();

        question1answers.add(a1);
        question1answers.add(a2);
        question1answers.add(a3);
        question1answers.add(a4);

        Question question1 = new Question( 1, "pytanie1", question1answers);

        Set<Question> quiz1questions = new HashSet<>();

        quiz1questions.add(question1);

        Quiz quiz1 = new Quiz(1, "quiz1", quiz1questions);

        answerService.addAnswer(a1);
        answerService.addAnswer(a2);
        answerService.addAnswer(a3);
        answerService.addAnswer(a4);
        questionService.addQuestion(question1);
        quizService.addQuiz(quiz1);

    }



}
