package pl.edu.wszib.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.quiz.model.Answer;
import pl.edu.wszib.quiz.model.Question;
import pl.edu.wszib.quiz.model.Quiz;

import pl.edu.wszib.quiz.service.IAnswerService;
import pl.edu.wszib.quiz.service.IQuestionService;
import pl.edu.wszib.quiz.service.IQuizService;
import pl.edu.wszib.quiz.session.SessionObject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CommonController {

    @Autowired
    IQuizService quizService;

    @Autowired
    IQuestionService questionService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("quizzes", this.quizService.getQuizzes());
        model.addAttribute("questions", this.questionService.getClass());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "main";
    }


}
