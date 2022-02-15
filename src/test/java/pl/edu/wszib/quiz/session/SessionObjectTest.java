package pl.edu.wszib.quiz.session;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wszib.quiz.model.User;

public class SessionObjectTest {

    @Test
    public void userLoggedTest(){
        User user = new User();
        SessionObject sessionObject = new SessionObject();
        sessionObject.setUser(user);

        boolean expectedResult = true;

        Assert.assertEquals(expectedResult, sessionObject.isLogged());
    }

    @Test
    public void userNotLoggedTest(){

        SessionObject sessionObject = new SessionObject();

        boolean expectedResult = false;

        Assert.assertEquals(expectedResult, sessionObject.isLogged());
    }



}
