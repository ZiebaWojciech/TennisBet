package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Data;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionType;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class BetSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    private TennisMatch tennisMatch;
    @ManyToOne
    private BetTicket betTicket;
    @NotNull
    private BetSelectionType betSelectionType;
    @NotNull
    private BetSelectionStatus betSelectionStatus;
    @NotNull
    private BigDecimal odd;
}
