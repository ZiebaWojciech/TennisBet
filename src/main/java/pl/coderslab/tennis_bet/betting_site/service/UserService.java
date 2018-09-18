package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getOne(int id);
    User getByUsername(String username);

    User saveNewUser(User user);
    User saveUserWithoutPassword(User user);

}
