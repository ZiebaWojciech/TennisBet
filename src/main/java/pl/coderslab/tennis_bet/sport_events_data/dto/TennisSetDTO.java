package pl.coderslab.tennis_bet.sport_events_data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TennisSetDTO {
    private int id;
    private List<TennisGameDTO> games = new ArrayList<>();
    private PlayerDTO tennisSetWinner;
}
