package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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

    public void addBetSelection(BetSelection betSelection) {
        this.betSelections.add(betSelection);
    }

    public void removeBetSelection(BetSelection betSelection) {
        this.betSelections.remove(betSelection);
    }

}
