package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.User;

import java.util.List;

public interface BetTicketRepository extends JpaRepository<BetTicket, Integer> {
    List<BetTicket> findAllByUser(User user);
}
