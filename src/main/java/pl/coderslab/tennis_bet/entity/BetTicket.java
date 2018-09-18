package pl.coderslab.tennis_bet.entity;

import lombok.Data;
import pl.coderslab.tennis_bet.entity.enumUtil.TicketStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BetTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "betTicket")
    private List<BetSelection> betSelections = new ArrayList<>();
    private BigDecimal stake;
    private TicketStatus ticketStatus;
}
