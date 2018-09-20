package pl.coderslab.tennis_bet.sport_events_data.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.Country;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.EventStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TennisMatchDTO {
    private Country country;
    private LocalDateTime timeOfStart;
    private Player playerOne;
    private Player playerTwo;
    private EventStatus status;
    private ResultDTO result;
    private Odd odds;
    private List<BetSelection> betSelectionsRelatedToMatch = new ArrayList<>();
}
