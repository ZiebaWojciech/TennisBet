package pl.coderslab.tennis_bet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.entity.BetSelection;
import pl.coderslab.tennis_bet.entity.BetTicket;

public interface BetSelectionRepository extends JpaRepository<BetSelection, Integer> {
}
