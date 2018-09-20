package pl.coderslab.tennis_bet.sport_events_data.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.Country;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
public class PlayerDTO {
    private int id;
    private String name;
    private String surname;
    private Country countryCode;
    private LocalDate birthday;
}
