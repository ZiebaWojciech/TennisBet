package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.service.PlayerService;
import pl.coderslab.tennis_bet.sport_events_data.service.ResultDTOService;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisSetDtoService;

@Service
public class ResultDTOServiceImpl implements ResultDTOService {
    private final TennisSetDtoService tennisSetDtoService;
    private final PlayerService playerService;

    @Autowired
    public ResultDTOServiceImpl(TennisSetDtoService tennisSetDtoService, PlayerService playerService) {
        this.tennisSetDtoService = tennisSetDtoService;
        this.playerService = playerService;
    }

    @Override
    public Result convertResultDtoToEntity(ResultDTO resultDTO) {
        Result newResult = new Result();
        newResult.setSets(tennisSetDtoService.getSetsFromResultDTO(resultDTO));
        if (resultDTO.getLooser() != null) {
            newResult.setLooser(playerService.playerByPlayerDTO(resultDTO.getLooser()));
        }
        if (resultDTO.getWinner() != null) {
            newResult.setWinner(playerService.playerByPlayerDTO(resultDTO.getWinner()));
        }
        return newResult;
    }
}
