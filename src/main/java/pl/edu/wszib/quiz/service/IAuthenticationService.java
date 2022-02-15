package pl.edu.wszib.quiz.service;


import pl.edu.wszib.quiz.model.User;
import pl.edu.wszib.quiz.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
    public User findUserById(Integer id);
}
