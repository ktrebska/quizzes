package pl.edu.wszib.quiz.database;


import pl.edu.wszib.quiz.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    User getUserById(int id);
}
