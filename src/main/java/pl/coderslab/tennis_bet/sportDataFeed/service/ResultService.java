package pl.coderslab.tennis_bet.sportDataFeed.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.*;

import java.util.List;

public interface ResultService {
    Result getOne(int id);
    Result getOneByEvent(Event event);

    List<Result> getAll();

    Result save(Result result);

    TennisSet getCurrentSet(Result result);

    TennisGame getCurrentGame(Result result);
}
