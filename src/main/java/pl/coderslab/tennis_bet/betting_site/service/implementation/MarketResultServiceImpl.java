package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.*;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketStatus;
import pl.coderslab.tennis_bet.betting_site.repository.BetTicketRepository;
import pl.coderslab.tennis_bet.betting_site.repository.MarketResultRepository;
import pl.coderslab.tennis_bet.betting_site.service.BetTicketService;
import pl.coderslab.tennis_bet.betting_site.service.MarketResultService;
import pl.coderslab.tennis_bet.betting_site.service.OddsService;
import pl.coderslab.tennis_bet.betting_site.service.WalletService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class MarketResultServiceImpl implements MarketResultService {
    @Autowired
    MarketResultRepository marketResultRepository;

    @Override
    public MarketResult save(MarketResult marketResult) {
        return marketResultRepository.save(marketResult);
    }
}
