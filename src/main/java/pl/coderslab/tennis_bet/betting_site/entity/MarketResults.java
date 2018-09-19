package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Random;

@Entity
@Getter
@Setter
public class MarketResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private BetSelectionType betSelectionType;

    @NotNull
    @ManyToOne
    private Result result;
}
