package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.betting_site.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;

public interface PlayerDTOService {
    int playerDtoHashCode(PlayerDTO playerDTO);
    Player convertPlayerDtoToEntity(PlayerDTO playerDto);
}
