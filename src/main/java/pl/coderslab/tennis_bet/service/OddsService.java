package pl.coderslab.tennis_bet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;
import pl.coderslab.tennis_bet.sportDataFeed.entity.AtpRankingPosition;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;
import pl.coderslab.tennis_bet.sportDataFeed.service.AtpRankingPositionService;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisMatchService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface OddsService {
    void recalculatePrematchOdds();
    BigDecimal[] prematchOddsCalculate(TennisMatch tennisMatch);
}

