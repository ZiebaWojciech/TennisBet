package pl.coderslab.tennis_bet.sport_events_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.betting_site.entity.TennisSet;

@Repository
public interface TennisSetRepository extends JpaRepository<TennisSet, Integer> {
}
