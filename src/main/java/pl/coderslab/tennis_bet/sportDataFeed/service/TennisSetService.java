package pl.coderslab.tennis_bet.sportDataFeed.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Result;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisSet;

import java.util.List;

public interface TennisSetService {
    TennisSet getOne(int id);

    List<TennisSet> getAll();

    TennisSet save(TennisSet tennisSet);

    long countGamesWonByPlayerOne(Result result);
    long countGamesWonByPlayerTwo(Result result);
}
