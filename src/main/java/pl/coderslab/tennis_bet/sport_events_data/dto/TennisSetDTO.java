package pl.coderslab.tennis_bet.sport_events_data.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.entity.TennisGame;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TennisSetDTO {
    private int id;
    private List<TennisGameDTO> games = new ArrayList<>();
    private PlayerDTO tennisSetWinner;
}
