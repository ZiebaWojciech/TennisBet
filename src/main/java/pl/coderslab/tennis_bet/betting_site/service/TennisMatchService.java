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
    TennisMatch setEventMarketResults(TennisMatch tennisMatch);

    int tennisMatchHashCode(TennisMatch tennisMatch);
    boolean unknownTennisMatch(TennisMatchDTO tennisMatchDTO, List<TennisMatch> allTennisMatches);
    boolean isSameTennisMatch(TennisMatchDTO checkedTennisMatch, TennisMatch tennisMatch);

    TennisMatch getTennisMatchByTennisMatchDTO(TennisMatchDTO tennisMatchDTO);

}
