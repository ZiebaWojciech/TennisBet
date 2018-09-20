package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisGameDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisSetDTO;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisGame;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisGameDtoService;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisGameService;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisMatchDTOService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TennisGameDtoServiceImpl implements TennisGameDtoService {
    @Autowired
    TennisMatchDTOService tennisMatchDTOService;
    @Autowired
    TennisGameService tennisGameService;

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
//            tennisGameService.save(tennisGame);
        }
        return tennisGames;
    }
}
