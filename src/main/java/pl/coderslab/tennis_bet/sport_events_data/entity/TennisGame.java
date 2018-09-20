package pl.coderslab.tennis_bet.sport_events_data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TennisGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int playerOnePoints;
    private int playerTwoPoints;

//    @ManyToOne
//    private Player tennisGameWinner;
}
