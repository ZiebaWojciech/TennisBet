package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.betting_site.entity.AtpRankingPosition;

import java.time.LocalDate;
import java.util.List;

public interface AtpRankingPositionService {
    AtpRankingPosition getOne(int id);
    List<AtpRankingPosition> getAllByPlayerId(int id);
    List<AtpRankingPosition> getAllByTime(LocalDate date);
    AtpRankingPosition save(AtpRankingPosition atpRankingPosition);
}
