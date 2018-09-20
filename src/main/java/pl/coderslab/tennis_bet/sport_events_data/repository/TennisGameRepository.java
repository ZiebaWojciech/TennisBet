package pl.coderslab.tennis_bet.sport_events_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.betting_site.entity.TennisGame;

@Repository
public interface TennisGameRepository extends JpaRepository<TennisGame, Integer> {
}
