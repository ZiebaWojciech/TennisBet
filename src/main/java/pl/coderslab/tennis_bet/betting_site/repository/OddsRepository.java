package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;

public interface OddsRepository extends JpaRepository<Odd, Integer> {
    Odd getAllByTennisMatch(TennisMatch tennisMatch);
}
