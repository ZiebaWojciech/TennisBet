package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisGameDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisSetDTO;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisGame;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisSet;
import pl.coderslab.tennis_bet.sport_events_data.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultDTOServiceImpl implements ResultDTOService {
    @Autowired
    TennisSetDtoService tennisSetDtoService;
    @Autowired
    PlayerService playerService;

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
