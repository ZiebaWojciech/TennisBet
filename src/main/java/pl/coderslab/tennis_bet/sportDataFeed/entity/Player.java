package pl.coderslab.tennis_bet.sportDataFeed.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.Country;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
