package pl.coderslab.tennis_bet.service;

import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sportDataFeed.entity.AtpRankingPosition;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OddsService {
    
    private BigDecimal prematchOddsCalculate(Event event) {
        List<AtpRankingPosition> playerOneAtpRankings = event.getPlayerOne().getAtpRankingPositions();
        List<AtpRankingPosition> playerTwoAtpRankings = event.getPlayerTwo().getAtpRankingPositions();

        event.getPlayerOne().getAtpRankingPositions().stream()
                .sorted( (a,b) ->  b.getDate().isAfter(a.getDate())? 1 : 0 )
                .findFirst();

        return null;

    }
}
