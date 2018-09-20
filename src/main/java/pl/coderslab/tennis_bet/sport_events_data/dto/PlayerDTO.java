package pl.coderslab.tennis_bet.sport_events_data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.Country;

import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties({"id"})
public class PlayerDTO {
    private String name;
    private String surname;
    @JsonProperty("country")
    private Country countryCode;
    private LocalDate birthday;
}
