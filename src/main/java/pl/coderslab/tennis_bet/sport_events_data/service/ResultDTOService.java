package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisSetDTO;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisGame;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisSet;

import java.util.List;


public interface ResultDTOService {
    Result convertResultDtoToEntity(ResultDTO resultDTO);
}


