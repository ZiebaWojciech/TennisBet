package pl.coderslab.tennis_bet.sport_events_data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TennisSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    private int sequenceNumber;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TennisGame> games = new ArrayList<>();

//    private int gamesWonByPlayerOne;
//    private int gamesWonByPlayerTwo;

//    private boolean inPlay;

//    @ManyToOne
//    private Player tennisSetWinner;

}
