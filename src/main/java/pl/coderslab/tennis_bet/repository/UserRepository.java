package pl.coderslab.tennis_bet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getAllByProjectsId(int id);
    User getByLogin(String login);
}
