package pl.coderslab.tennis_bet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByLogin(String login);
}
