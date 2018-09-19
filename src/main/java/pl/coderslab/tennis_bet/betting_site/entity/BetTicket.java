package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.TicketStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class BetTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "betTicket")
    private List<BetSelection> betSelections = new ArrayList<>();
    private BigDecimal stake;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    public void addBetSelection(BetSelection betSelection) {
        this.betSelections.add(betSelection);
    }

    public void removeBetSelection(BetSelection betSelection) {
        this.betSelections.remove(betSelection);
    }

}
