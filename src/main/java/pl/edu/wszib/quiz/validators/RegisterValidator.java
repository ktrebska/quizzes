package pl.edu.wszib.quiz.validators;

import pl.edu.wszib.quiz.exceptions.RegisterValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidator {
    public static void validateName(String name) {
        validateBasics(name);
    }


    private static void validateBasics(String value) {
        if(value == null) {
            throw new RegisterValidationException();
        }

        Pattern pattern = Pattern.compile("[A-Z][a-z]+");
        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches()) {
            throw new RegisterValidationException();
        }
    }
}
