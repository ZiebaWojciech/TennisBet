package pl.coderslab.tennis_bet.sport_events_data.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.MarketResults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultDTO {
    private List<TennisSetDTO> sets = new ArrayList<>();
    private TennisMatchDTO tennisMatch;
    private PlayerDTO winner;
    private PlayerDTO looser;
    private List<MarketResults> marketResults = new ArrayList<>();
}
