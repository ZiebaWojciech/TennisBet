package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.EventStatus;

import java.util.List;

@Repository
public interface TennisMatchRepository extends JpaRepository<TennisMatch, Integer> {
    List<TennisMatch> getAllByStatusEqualsOrderByTimeOfStartDesc(EventStatus eventStatus);
}
