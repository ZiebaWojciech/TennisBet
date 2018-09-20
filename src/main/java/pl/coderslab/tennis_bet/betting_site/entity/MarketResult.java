package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class MarketResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BetSelectionType betSelectionType;

    @NotNull
    @ManyToOne
    private Result result;

    public MarketResult() {
    }

    public MarketResult(@NotNull BetSelectionType betSelectionType, @NotNull Result result) {
        this.betSelectionType = betSelectionType;
        this.result = result;
    }
}
