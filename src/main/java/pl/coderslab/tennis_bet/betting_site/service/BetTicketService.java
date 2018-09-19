package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;

import java.math.BigDecimal;
import java.util.UUID;

public interface BetTicketService {
    BetTicket save(BetTicket betTicket);

    boolean submitTicket(BigDecimal stake, BetTicket betTicket);
    BetTicket removeBetSelectionFromTicket(BetTicket betTicket, UUID temporalId);
}
