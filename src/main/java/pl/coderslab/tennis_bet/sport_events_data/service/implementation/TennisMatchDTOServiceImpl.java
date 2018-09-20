package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;
import pl.coderslab.tennis_bet.sport_events_data.service.PlayerDTOService;
import pl.coderslab.tennis_bet.sport_events_data.service.PlayerService;
import pl.coderslab.tennis_bet.sport_events_data.service.ResultDTOService;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisMatchDTOService;

import java.util.Objects;

@Service
public class TennisMatchDTOServiceImpl implements TennisMatchDTOService {
    @Autowired
    PlayerDTOService playerDTOService;
    @Autowired
    ResultDTOService resultDTOService;
    @Override
    public int tennisMatchDtoHashCode(TennisMatchDTO tennisMatchDTO) {
        return Objects.hash(tennisMatchDTO.getCountry(), tennisMatchDTO.getTimeOfStart(), playerDTOService.playerDtoHashCode(tennisMatchDTO.getPlayerOneDto()), playerDTOService.playerDtoHashCode(tennisMatchDTO.getPlayerTwoDto()));
    }
    @Override
    public TennisMatch convertTennisMatchDtoToEntity(TennisMatchDTO tennisMatchDto) {
        TennisMatch newTennisMatch = new TennisMatch();
        newTennisMatch.setCountry(tennisMatchDto.getCountry());
        newTennisMatch.setTimeOfStart(tennisMatchDto.getTimeOfStart());
        newTennisMatch.setPlayerOne(playerDTOService.convertPlayerDtoToEntity(tennisMatchDto.getPlayerOneDto()));
        newTennisMatch.setPlayerTwo(playerDTOService.convertPlayerDtoToEntity(tennisMatchDto.getPlayerTwoDto()));
        newTennisMatch.setStatus(tennisMatchDto.getStatus());
        if(tennisMatchDto.getResultDto() != null){
            newTennisMatch.setResult(resultDTOService.convertResultDtoToEntity(tennisMatchDto.getResultDto()));
        }
        return newTennisMatch;
    }

}
