package pl.coderslab.tennis_bet.sportDataFeed.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.Country;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Country countryCode;
    private Date birthday;

}
