package pl.coderslab.tennis_bet.sport_events_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.sport_events_data.entity.AtpRankingPosition;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AtpRankingPositionRepository extends JpaRepository<AtpRankingPosition, Integer> {
    List<AtpRankingPosition> getAllByPlayerId(int id);
    List<AtpRankingPosition> getAllByDate(LocalDate date);
}
