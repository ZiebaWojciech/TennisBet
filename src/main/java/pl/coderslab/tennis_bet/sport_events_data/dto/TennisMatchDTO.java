package pl.coderslab.tennis_bet.sport_events_data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.Country;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.EventStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties({"id"})
public class TennisMatchDTO {
    private Country country;
    private LocalDateTime timeOfStart;
    @JsonProperty("playerOne")
    private PlayerDTO playerOneDto;
    @JsonProperty("playerTwo")
    private PlayerDTO playerTwoDto;
    private EventStatus status;
    @JsonProperty("result")
    private ResultDTO resultDto;

}
