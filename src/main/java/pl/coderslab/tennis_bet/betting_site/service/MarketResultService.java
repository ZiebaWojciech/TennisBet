package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.MarketResult;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;

import java.math.BigDecimal;

public interface MarketResultService {
    MarketResult save(MarketResult marketResult);
}
