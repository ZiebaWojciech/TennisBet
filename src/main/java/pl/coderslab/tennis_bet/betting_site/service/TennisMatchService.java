package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;

import java.util.List;

public interface TennisMatchService {
    TennisMatch getOne(int id);
    List<TennisMatch> getAll();
    List<TennisMatch> getUpcomingTennisMatches();

    TennisMatch save(TennisMatch tennisMatch);

    boolean isEventStatusChanged(TennisMatch updatedTennisMatch);

    int tennisMatchHashCode(TennisMatch tennisMatch);
    boolean isNewTennisMatch(TennisMatchDTO tennisMatchDTO, List<TennisMatch> allTennisMatches);
    boolean isSameTennisMatch(TennisMatchDTO checkedTennisMatch, TennisMatch tennisMatch);

}
