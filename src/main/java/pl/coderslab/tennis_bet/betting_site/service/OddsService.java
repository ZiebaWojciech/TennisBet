package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;

import java.math.BigDecimal;

public interface OddsService {
    void recalculatePrematchOdds();
    BigDecimal[] prematchOddsCalculate(TennisMatch tennisMatch);
}

