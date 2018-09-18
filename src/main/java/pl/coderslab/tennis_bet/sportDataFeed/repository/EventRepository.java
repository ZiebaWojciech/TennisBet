package pl.coderslab.tennis_bet.sportDataFeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.EventStatus;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> getAllByStatusEqualsOrderByTimeOfStartDesc(EventStatus eventStatus);
}
