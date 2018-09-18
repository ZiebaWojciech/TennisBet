package pl.coderslab.tennis_bet.sportDataFeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.EventStatus;

import java.util.List;

@Repository
public interface TennisMatchRepository extends JpaRepository<TennisMatch, Integer> {
    List<TennisMatch> getAllByStatusEqualsOrderByTimeOfStartDesc(EventStatus eventStatus);
}
