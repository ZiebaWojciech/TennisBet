package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BetTicketService {
    List<BetTicket> getAllByUser(User user);
    BetTicket save(BetTicket betTicket);

    boolean submitTicket(BigDecimal stake, BetTicket betTicket);
    BetTicket removeBetSelectionFromTicket(BetTicket betTicket, UUID temporalId);

    boolean isTicketCompleted(BetTicket betTicket);

    void resolveBetTicket(BetTicket betTicket);
}
