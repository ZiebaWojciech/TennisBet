package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;

import java.math.BigDecimal;

public interface OddsService {
    Odd getOddsOfEvent(TennisMatch tennisMatch);

    void recalculatePrematchOdds();

    BigDecimal[] prematchOddsCalculate(TennisMatch tennisMatch);
}

