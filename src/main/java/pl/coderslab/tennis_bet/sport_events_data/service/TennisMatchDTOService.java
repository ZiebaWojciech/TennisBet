package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;

public interface TennisMatchDTOService {
    int tennisMatchDtoHashCode(TennisMatchDTO tennisMatchDTO);
    TennisMatch convertTennisMatchDtoToEntity(TennisMatchDTO tennisMatchDto);
}
