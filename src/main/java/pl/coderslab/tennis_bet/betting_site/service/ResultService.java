package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisGame;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisSet;

import java.util.List;

public interface ResultService {
    Result getOne(int id);

    List<Result> getAll();

    Result save(Result result);

    void delete(Result result);

    void deleteById(int id);
}