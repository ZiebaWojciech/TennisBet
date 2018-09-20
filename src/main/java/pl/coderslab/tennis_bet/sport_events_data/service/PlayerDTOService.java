package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;
import pl.coderslab.tennis_bet.betting_site.entity.Player;

public interface PlayerDTOService {
    int playerDtoHashCode(PlayerDTO playerDTO);
    Player convertPlayerDtoToEntity(PlayerDTO playerDto);
}
