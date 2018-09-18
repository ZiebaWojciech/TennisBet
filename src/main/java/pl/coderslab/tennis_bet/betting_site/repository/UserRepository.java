package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.betting_site.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUsername(String username);
}
