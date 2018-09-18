package pl.coderslab.tennis_bet.sportDataFeed.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;

import java.util.List;

public interface TennisMatchService {
    TennisMatch getOne(int id);
    List<TennisMatch> getAll();
    List<TennisMatch> getUpcomingTennisMatches();

    TennisMatch save(TennisMatch tennisMatch);
}
