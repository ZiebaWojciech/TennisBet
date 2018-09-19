package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.sport_events_data.entity.*;

import java.util.List;

public interface TennisGameService {
    TennisGame getOne(int id);
    List<TennisGame> getAll();
    TennisGame save(TennisGame tennisGame);
}
