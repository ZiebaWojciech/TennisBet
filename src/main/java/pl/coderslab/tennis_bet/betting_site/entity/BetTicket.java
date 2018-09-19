package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class BetTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    private User user;
    @CreationTimestamp
    private LocalDateTime timeOfCreation;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "betTicket")
    private List<BetSelection> betSelections = new ArrayList<>();
    private BigDecimal stake;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BetTicketStatus betTicketStatus;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BetTicketResult betTicketResult;

    private BigDecimal totalOdd;

    private int uncheckedBetSelectionsCounter;

    public void addBetSelection(BetSelection betSelection) {
        this.betSelections.add(betSelection);
    }

    public void removeBetSelection(BetSelection betSelection) {
        this.betSelections.remove(betSelection);
    }

    public void decrementUncheckedBetSelectionsCounter() {
        this.uncheckedBetSelectionsCounter--;
    }
}
