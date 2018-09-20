package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.TennisSet;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisSetDTO;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisGameDtoService;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisSetDtoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TennisSetDtoServiceImpl implements TennisSetDtoService {
    private final TennisGameDtoService tennisGameDtoService;

    @Autowired
    public TennisSetDtoServiceImpl(TennisGameDtoService tennisGameDtoService) {
        this.tennisGameDtoService = tennisGameDtoService;
    }

    @Override
    public List<TennisSet> getSetsFromResultDTO(ResultDTO resultDto) {
        List<TennisSetDTO> tennisSetDtos = resultDto.getSetDtos();
        List<TennisSet> tennisSets = new ArrayList<>();
        for(TennisSetDTO tennisSetDto : tennisSetDtos) {
            TennisSet tennisSet = new TennisSet();
            tennisSet.setGames(tennisGameDtoService.getGamesFromSetDTO(tennisSetDto));
            tennisSets.add(tennisSet);
        }
        return tennisSets;
    }
}
