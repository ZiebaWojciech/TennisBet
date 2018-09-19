package pl.coderslab.tennis_bet.sport_events_data.entity;

import lombok.*;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.Country;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    private int id;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Country countryCode;
    private LocalDate birthday;

}
