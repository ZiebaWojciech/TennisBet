package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;
import pl.coderslab.tennis_bet.betting_site.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.service.PlayerDTOService;

import java.util.Objects;

@Service
public class PlayerDTOServiceImpl implements PlayerDTOService {
    @Override
    public int playerDtoHashCode(PlayerDTO playerDTO) {
        return Objects.hash(playerDTO.getName(), playerDTO.getSurname(),playerDTO.getCountryCode(), playerDTO.getBirthday());
    }

    @Override
    public Player convertPlayerDtoToEntity(PlayerDTO playerDto) {
        Player newPlayer = new Player();
        newPlayer.setName(playerDto.getName());
        newPlayer.setCountry(playerDto.getCountryCode());
        newPlayer.setSurname(playerDto.getSurname());
        newPlayer.setBirthday(playerDto.getBirthday());
        return newPlayer;
    }
}


