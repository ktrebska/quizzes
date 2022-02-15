package pl.edu.wszib.quiz.service.impl;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.quiz.configuration.AppConfiguration;
import pl.edu.wszib.quiz.service.IQuizService;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public class QuizServiceTest extends TestCase {

    @Autowired
    IQuizService quizService;

    @Test
    public void testCountCorrectAnswers() {

        int expectedResult = 1;
        Assert.assertEquals(expectedResult, quizService.countCorrectAnswers(1));
    }
}