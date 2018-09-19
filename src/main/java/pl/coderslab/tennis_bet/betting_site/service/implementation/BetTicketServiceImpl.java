package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketStatus;
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
    public List<BetTicket> getAllByUser(User user) {
        return betTicketRepository.findAllByUser(user);
    }

    @Override
    public BetTicket getOne(int id) {
        return betTicketRepository.getOne(id);
    }

    @Override
    public BetTicket save(BetTicket betTicket) {
        return betTicketRepository.save(betTicket);
    }

    @Override
    public boolean submitTicket(BigDecimal stake, BetTicket betTicket) {
        if (hasOddsChanged(betTicket)) {
            return false;
        }
        betTicket.setStake(stake);
        betTicket.setBetTicketStatus(BetTicketStatus.SUBMITTED);
        betTicket.getBetSelections().forEach(v -> v.setBetSelectionStatus(BetSelectionStatus.SUBMITTED));
        betTicket.getBetSelections().forEach(v -> v.setBetSelectionResult(BetSelectionResult.ONGOING));
        betTicket.setBetTicketResult(BetTicketResult.ONGOING);
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

    @Override
    public boolean isTicketCompleted(BetTicket betTicket) {
        List<BetSelection> betSelections = betTicket.getBetSelections();
        return betSelections.stream().filter(v->v.getBetSelectionStatus().equals(BetSelectionStatus.FINISHED)).count() == betSelections.size();
    }

    @Override
    public void resolveBetTicket(BetTicket betTicket) {
        betTicket.setBetTicketStatus(BetTicketStatus.ENDED_NOT_CASHED);
        List<BetSelection> betSelections = betTicket.getBetSelections();
        boolean isAnyBetSelecionLost = betSelections.stream().anyMatch(v -> v.getBetSelectionResult().equals(BetSelectionResult.LOST));
        boolean isAllBetSelecionVoid= betSelections.stream().allMatch(v -> v.getBetSelectionResult().equals(BetSelectionResult.VOID));
        if(isAnyBetSelecionLost){
            betTicket.setBetTicketResult(BetTicketResult.LOST);
        } else if(isAllBetSelecionVoid){
            betTicket.setBetTicketResult(BetTicketResult.CANCELLED);
        } else {
            betTicket.setBetTicketResult(BetTicketResult.WIN);
        }
        save(betTicket);
    }
}
