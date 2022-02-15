package pl.edu.wszib.quiz.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.ArrayAnswer;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;
import pl.edu.wszib.quiz.service.IAnswerService;
import pl.edu.wszib.quiz.service.IQuestionService;
import pl.edu.wszib.quiz.service.IQuizService;
import pl.edu.wszib.quiz.session.SessionObject;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping(value = "/quiz")
public class QuizController {

    @Autowired
    IQuizService quizService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/solve/{quizId}", method = RequestMethod.GET)
    public String solveQuiz(@PathVariable Integer quizId, Model model) {
        model.addAttribute("currentQuiz", this.quizService.findById(quizId));
        model.addAttribute("ans", new Answer());
        model.addAttribute("correctAnswers", this.quizService.findById(quizId).getCorrectAnswersIds() );
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "solve";
    }

    @RequestMapping(value = "/solve/{quizId}", method = RequestMethod.POST)
    public String solveQuiz(@PathVariable Integer quizId,  @ModelAttribute Answer ans, RedirectAttributes redirectAttributes) {
        int points = this.quizService.solve(quizId, ans);
        int maxpoints = this.quizService.countCorrectAnswers(quizId);
        redirectAttributes.addAttribute("score", points);
        redirectAttributes.addAttribute("maxscore", maxpoints);
        return "redirect:/quiz/result";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String result (Model model, @RequestParam("score") String score, @RequestParam("maxscore") String maxscore) {

        model.addAttribute("score", score);
        model.addAttribute("maxscore", maxscore);
        model.addAttribute("logged", this.sessionObject.isLogged());

        return "result";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addQuiz (Model model) {

        model.addAttribute("quiz", new Quiz());
        model.addAttribute("question", new Question());
        model.addAttribute("a1", new ArrayAnswer());
        model.addAttribute("logged", this.sessionObject.isLogged());

        return "addquiz";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addQuiz (@ModelAttribute Quiz quiz, @ModelAttribute Question question, @ModelAttribute ArrayAnswer a1) {

        this.quizService.createQuiz(quiz, question, a1);
        return "redirect:/main";
    }

}
