package pl.coderslab.tennis_bet.sport_events_data.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class TennisGameDTO {
    private int id;
    private int playerOnePoints;
    private int playerTwoPoints;
    private PlayerDTO tennisGameWinner;
}
