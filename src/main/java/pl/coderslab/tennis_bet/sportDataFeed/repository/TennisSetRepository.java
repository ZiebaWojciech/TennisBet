package pl.coderslab.tennis_bet.sportDataFeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisSet;

@Repository
public interface TennisSetRepository extends JpaRepository<TennisSet, Integer> {
}
