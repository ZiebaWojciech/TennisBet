package pl.coderslab.tennis_bet.sport_events_data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TennisGame {
    @Id
    private int id;

    private boolean inPlay;

    private int playerOnePoints;
    private int playerTwoPoints;

    @ManyToOne
    private Player tennisGameWinner;
}
