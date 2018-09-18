package pl.coderslab.tennis_bet.entity;

import lombok.Data;
import pl.coderslab.tennis_bet.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.entity.enumUtil.BetSelectionType;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;

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
    private Event event;
    @ManyToOne
    private BetTicket betTicket;
    @NotNull
    private BetSelectionType betSelectionType;
    @NotNull
    private BetSelectionStatus betSelectionStatus;
    @NotNull
    private BigDecimal odd;
}
