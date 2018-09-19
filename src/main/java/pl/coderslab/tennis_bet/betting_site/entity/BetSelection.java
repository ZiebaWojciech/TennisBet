package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private BetSelectionStatus betSelectionStatus;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BetSelectionResult betSelectionResult;
    @NotNull
    private BigDecimal odd;

    @NotNull
    @Transient
    private UUID temporalId;
}
