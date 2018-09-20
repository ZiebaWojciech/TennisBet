package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TennisSet> sets = new ArrayList<>();

    @NotNull
    @OneToOne
    private TennisMatch tennisMatch;

    @ManyToOne
    private Player winner;
    @ManyToOne
    private Player looser;
    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL)
    private List<MarketResult> marketResults = new ArrayList<>();
}

