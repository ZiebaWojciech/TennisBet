package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisSet;

import java.util.List;

public interface TennisSetService {
    TennisSet getOne(int id);

    List<TennisSet> getAll();

    TennisSet save(TennisSet tennisSet);
}
