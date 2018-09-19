package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.MarketResults;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionType;
import pl.coderslab.tennis_bet.betting_site.repository.BetSelectionRepository;
import pl.coderslab.tennis_bet.betting_site.service.BetSelectionService;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.service.BetTicketService;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.EventStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BetSelectionServiceImpl implements BetSelectionService {
    @Autowired
    BetSelectionRepository betSelectionRepository;
    @Autowired
    BetTicketService betTicketService;


    @Override
    public BetTicket addNewBetSelectionToBetTicket(BetTicket betTicket, String betSelectionType, BigDecimal odd, TennisMatch tennisMatch) {
        BetSelection betSelection = new BetSelection();
        betSelection.setTemporalId(UUID.randomUUID());
        betSelection.setBetSelectionStatus(BetSelectionStatus.PENDING);
        betSelection.setTennisMatch(tennisMatch);
        betSelection.setOdd(odd);
        betSelection.setBetTicket(betTicket);
        switch (betSelectionType) {
            case "PlayerOne":
                betSelection.setBetSelectionType(BetSelectionType.PLAYER_ONE_WINS);
                break;
            case "PlayerTwo":
                betSelection.setBetSelectionType(BetSelectionType.PLAYER_TWO_WINS);
                break;
        }
        betTicket.addBetSelection(betSelection);
        return betTicket;
    }

    @Override
    public boolean isBetUniqueForEvent(BetTicket betTicket, TennisMatch tennisMatch) {
        return betTicket.getBetSelections().stream().noneMatch(v -> v.getTennisMatch().getId() == tennisMatch.getId());
    }

    @Override
    public void resolveBetAfterEventStatusChange(TennisMatch tennisMatch) {
        List<BetSelection> betSelectionsRelatedToTennisMatch = tennisMatch.getBetSelectionsRelatedToMatch();
        for(BetSelection betSelection : betSelectionsRelatedToTennisMatch){
            if (tennisMatch.getStatus() == EventStatus.CANCELLED) {
                betSelection.setBetSelectionStatus(BetSelectionStatus.FINISHED);
                betSelection.setBetSelectionResult(BetSelectionResult.VOID);

                BigDecimal updatedTotalOdd = betTicketService.calculateTotalOdd(betSelection.getBetTicket());
                betSelection.getBetTicket().setTotalOdd(updatedTotalOdd);

                betSelection.getBetTicket().decrementUncheckedBetSelectionsCounter();
            } else if (tennisMatch.getStatus() == EventStatus.COMPLETED) {
                resolvingBetSelectionAfterCompletedEvent(betSelection, tennisMatch);
                betSelection.getBetTicket().decrementUncheckedBetSelectionsCounter();
            }
            betTicketService.save(betSelection.getBetTicket());
        }
    }

    @Override
    public void resolvingBetSelectionAfterCompletedEvent(BetSelection betSelection, TennisMatch tennisMatch) {
        betSelection.setBetSelectionStatus(BetSelectionStatus.FINISHED);
        for (MarketResults marketResults : tennisMatch.getResult().getMarketResults()) {
            if (marketResults.getBetSelectionType().equals(betSelection.getBetSelectionType())) {
                betSelection.setBetSelectionResult(BetSelectionResult.WON);
            } else {
                betSelection.setBetSelectionResult(BetSelectionResult.LOST);
            }
        }
    }
}
