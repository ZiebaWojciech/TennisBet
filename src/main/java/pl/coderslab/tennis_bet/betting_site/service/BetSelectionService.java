package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;

import java.math.BigDecimal;

public interface BetSelectionService {
    BetTicket addNewBetSelectionToBetTicket(BetTicket betTicket, String betSelectionType, BigDecimal odd, TennisMatch tennisMatch);
    boolean isBetUniqueForEvent(BetTicket betTicket, TennisMatch tennisMatch);

    void resolveBetAfterEventStatusChange(TennisMatch tennisMatch);
    void resolvingBetSelectionAfterCompletedEvent(BetSelection betSelection, TennisMatch tennisMatch);

}
