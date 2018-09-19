package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Data;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class Odd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    private TennisMatch tennisMatch;

    //TODO service to refresh odds (schedule) but only before match (status)
    private BigDecimal playerOneWinningOdd;
    private BigDecimal playerTwoWinningOdd;

    public Odd() {
    }

    public Odd(@NotNull TennisMatch tennisMatch, BigDecimal playerOneWinningOdd, BigDecimal playerTwoWinningOdd) {
        this.tennisMatch = tennisMatch;
        this.playerOneWinningOdd = playerOneWinningOdd;
        this.playerTwoWinningOdd = playerTwoWinningOdd;
    }
}
