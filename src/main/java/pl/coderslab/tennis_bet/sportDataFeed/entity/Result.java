package pl.coderslab.tennis_bet.sportDataFeed.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Result {
    @Id
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TennisSet> sets = new ArrayList<>();
    @ManyToOne
    private Player winner;
    @ManyToOne
    private Player looser;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Event event;

    private int setsWonByPlayerOne;
    private int setsWonByPlayerTwo;
}

