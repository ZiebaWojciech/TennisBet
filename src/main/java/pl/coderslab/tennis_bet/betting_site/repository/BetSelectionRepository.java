package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;

public interface BetSelectionRepository extends JpaRepository<BetSelection, Integer> {
}
