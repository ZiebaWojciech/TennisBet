package pl.coderslab.tennis_bet.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sportDataFeed.entity.AtpRankingPosition;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.service.AtpRankingPositionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class OddsService {
    private final AtpRankingPositionService atpRankingPositionService;
    private final double SPORTBOOK_MARGIN_FACTOR = 1.08;

    @Autowired
    public OddsService(AtpRankingPositionService atpRankingPositionService) {
        this.atpRankingPositionService = atpRankingPositionService;
    }

    public BigDecimal[] prematchOddsCalculate(Event event) {
        Player playerOne = event.getPlayerOne();
        Player playerTwo = event.getPlayerTwo();

        int pointsDifference = getAtpPoint(playerOne) - getAtpPoint(playerTwo);
        int standingIntervalDifference = getPointsInterval(playerOne) - getPointsInterval(playerTwo);
        double ageDifference = getPlayerAge(playerOne) - getPlayerAge(playerTwo);
        int homePlayFactorDifference = getHomePlayFactor(playerOne, event) - getHomePlayFactor(playerTwo, event);

        BigDecimal[] odds = new BigDecimal[2];

        double playerOneWinningOddWithoutMargin = (2.30 + 0.0002 * pointsDifference + 0.187 * standingIntervalDifference - 0.064 * ageDifference + 0.497 * homePlayFactorDifference);

        double playerOneWinningProbabilityWithoutMargin = 1 / playerOneWinningOddWithoutMargin;
        double playerTwoWinningProbabilityWithoutMargin = 1 - playerOneWinningProbabilityWithoutMargin;

        BigDecimal playerOneWinningOddWithMargin = new BigDecimal(1 / (SPORTBOOK_MARGIN_FACTOR * playerOneWinningProbabilityWithoutMargin)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal playerTwoWinningOddWithMargin = new BigDecimal(1 / (SPORTBOOK_MARGIN_FACTOR * playerTwoWinningProbabilityWithoutMargin)).setScale(2, BigDecimal.ROUND_HALF_UP);

        odds[0] = playerOneWinningOddWithMargin;
        odds[1] = playerTwoWinningOddWithMargin;

        return odds;
    }

    private int getHomePlayFactor(Player player, Event event) {
        return player.getCountryCode().equals(event.getCountry()) ? 1 : 0;
    }

    private double getPlayerAge(Player player) {
        return (Period.between(player.getBirthday(), LocalDate.now()).toTotalMonths()) / 12.0;
    }


    private int getPointsInterval(Player player) {
        int standing = getPlayerCurrentAtpRanking(player).getPoints();
        if (standing < 400) return 0;
        else if (standing < 800) return 1;
        else if (standing < 1200) return 2;
        else if (standing < 2000) return 3;
        else return 4;
    }


    private int getAtpPoint(Player player) {
        return getPlayerCurrentAtpRanking(player).getPoints();
    }

    private AtpRankingPosition getPlayerCurrentAtpRanking(Player player) {
        return atpRankingPositionService.getAllByPlayerId(player.getId())
                .stream()
                .min((first, second) -> Period.between(first.getDate(), second.getDate()).getDays())
                .orElse(null);
    }

}
