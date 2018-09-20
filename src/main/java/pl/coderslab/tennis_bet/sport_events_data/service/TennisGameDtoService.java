package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.betting_site.entity.TennisGame;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisGameDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisSetDTO;

import java.util.List;

public interface TennisGameDtoService {
    TennisGame convertTennisGameDtoToEntity(TennisGameDTO tennisGameDTO);
    List<TennisGame> getGamesFromSetDTO(TennisSetDTO tennisSetDTO);
}


