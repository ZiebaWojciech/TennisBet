package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.TennisGame;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisGameDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisSetDTO;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisGameDtoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TennisGameDtoServiceImpl implements TennisGameDtoService {

    @Override
    public TennisGame convertTennisGameDtoToEntity(TennisGameDTO tennisGameDTO) {
        TennisGame tennisGame = new TennisGame();
        tennisGame.setPlayerOnePoints(tennisGameDTO.getPlayerOnePoints());
        tennisGame.setPlayerTwoPoints(tennisGameDTO.getPlayerTwoPoints());
        return tennisGame;
    }

    @Override
    public List<TennisGame> getGamesFromSetDTO(TennisSetDTO tennisSetDTO) {
        List<TennisGameDTO> tennisGameDtos = tennisSetDTO.getGamesDtos();
        List<TennisGame> tennisGames = new ArrayList<>();
        for (TennisGameDTO tennisGameDto : tennisGameDtos) {
            TennisGame tennisGame = convertTennisGameDtoToEntity(tennisGameDto);
            tennisGames.add(tennisGame);
        }
        return tennisGames;
    }
}
