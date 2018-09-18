package pl.coderslab.tennis_bet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.entity.BetTicket;
import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;

public interface BetTicketRepository extends JpaRepository<BetTicket, Integer> {
}
