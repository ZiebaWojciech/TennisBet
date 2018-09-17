package pl.coderslab.tennis_bet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sportDataFeed.entity.AtpRankingPosition;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.service.AtpRankingPositionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OddsService {
    private final AtpRankingPositionService atpRankingPositionService;

    @Autowired
    public OddsService(AtpRankingPositionService atpRankingPositionService) {
        this.atpRankingPositionService = atpRankingPositionService;
    }

    private BigDecimal prematchOddsCalculate(Event event) {
        Player playerOne = event.getPlayerOne();
        Player playerTwo = event.getPlayerTwo();

        AtpRankingPosition playerOneCurrentAptRanking = atpRankingPositionService.getAllByPlayerId(playerOne.getId()).stream()
                .sorted( (a,b) ->  b.getDate().isAfter(a.getDate())? 1 : 0 )
                .findFirst()
                .orElse(null);

        AtpRankingPosition playerTwoCurrentAptRanking = atpRankingPositionService.getAllByPlayerId(playerTwo.getId()).stream()
                .sorted( (a,b) ->  b.getDate().isAfter(a.getDate())? 1 : 0 )
                .findFirst()
                .orElse(null);

        return null;

    }
}
