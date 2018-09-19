package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BetTicketService {
    List<BetTicket> getAllByUser(User user);
    List<BetTicket> getAllByUserAndWonAndNotCashed(User user);
    BetTicket getOne(int id);

    BetTicket save(BetTicket betTicket);

    boolean hasOddsChanged(BetTicket betTicket);
    void removeBetSelectionFromTicket(BetTicket betTicket, UUID temporalId);
    void submitTicket(BigDecimal stake, BetTicket betTicket);

    BigDecimal calculateTotalOdd(BetTicket betTicket);

    boolean isTicketCompleted(BetTicket betTicket);

    void resolveBetTicket(BetTicket betTicket);

    void cashOutTicket(BetTicket betTicket, User user);
}
