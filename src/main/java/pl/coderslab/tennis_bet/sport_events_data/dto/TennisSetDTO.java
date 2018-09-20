package pl.coderslab.tennis_bet.sport_events_data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties({"id"})
public class TennisSetDTO {
    @JsonProperty("games")
    private List<TennisGameDTO> gamesDtos = new ArrayList<>();
//    private PlayerDTO tennisSetWinner;
}
