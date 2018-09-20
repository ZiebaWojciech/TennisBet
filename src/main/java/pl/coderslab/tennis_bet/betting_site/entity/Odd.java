package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Odd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    private TennisMatch tennisMatch;

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
