package pl.coderslab.tennis_bet.sportDataFeed.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.*;

import java.util.List;

public interface TennisGameService {
    TennisGame getOne(int id);
    List<TennisGame> getAll();
    TennisGame save(TennisGame tennisGame);
}
