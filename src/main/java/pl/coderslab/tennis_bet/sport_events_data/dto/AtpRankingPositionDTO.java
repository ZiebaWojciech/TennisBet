package pl.coderslab.tennis_bet.sport_events_data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties({"id"})
public class AtpRankingPositionDTO {
    private long id;
    private PlayerDTO player;
    private LocalDate date;
    private int points;
    private int standing;
}
