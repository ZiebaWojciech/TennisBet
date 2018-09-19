package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.TicketStatus;
import pl.coderslab.tennis_bet.betting_site.repository.BetTicketRepository;
import pl.coderslab.tennis_bet.betting_site.service.BetTicketService;
import pl.coderslab.tennis_bet.betting_site.service.OddsService;
import pl.coderslab.tennis_bet.betting_site.service.WalletService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BetTicketServiceImpl implements BetTicketService {
    @Autowired
    BetTicketRepository betTicketRepository;
    @Autowired
    WalletService walletService;
    @Autowired
    OddsService oddsService;

    @Override
    public BetTicket save(BetTicket betTicket) {
        return betTicketRepository.save(betTicket);
    }

    @Override
    public boolean submitTicket(BigDecimal stake, BetTicket betTicket) {
        if (!hasOddsChanged(betTicket)) {
            return false;
        }
        betTicket.setStake(stake);
        betTicket.setTicketStatus(TicketStatus.SUBMITTED);
        betTicket.getBetSelections().forEach(v -> v.setBetSelectionStatus(BetSelectionStatus.SUBMITTED));
        walletService.deductFromBalance(betTicket.getUser().getWallet(), stake);
        save(betTicket);
        return true;


    }

    private boolean hasOddsChanged(BetTicket betTicket) {
        for (BetSelection betSelection : betTicket.getBetSelections()) {
            Odd currentOdds = oddsService.getOddsOfEvent(betSelection.getTennisMatch());
            switch (betSelection.getBetSelectionType()) {
                case PLAYER_ONE_WINS:
                    if (betSelection.getOdd().compareTo(currentOdds.getPlayerOneWinningOdd()) == 0) return false;
                case PLAYER_TWO_WINS:
                    if (betSelection.getOdd().compareTo(currentOdds.getPlayerTwoWinningOdd()) == 0) return false;
            }
        }
        return true;
    }

    @Override
    public BetTicket removeBetSelectionFromTicket(BetTicket betTicket, UUID temporalId) {
        BetSelection betSelection = betTicket.getBetSelections().stream().filter(v -> v.getTemporalId().compareTo(temporalId) == 0).findFirst().get();
        betTicket.removeBetSelection(betSelection);
        return betTicket;
    }
}
