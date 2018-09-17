package pl.coderslab.tennis_bet.sportDataFeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisGame;

@Repository
public interface TennisGameRepository extends JpaRepository<TennisGame, Integer> {
}
