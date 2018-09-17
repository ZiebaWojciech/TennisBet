package pl.coderslab.tennis_bet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sportDataFeed.entity.AtpRankingPosition;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.service.AtpRankingPositionService;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OddsService {
    private final AtpRankingPositionService atpRankingPositionService;

    @Autowired
    public OddsService(AtpRankingPositionService atpRankingPositionService) {
        this.atpRankingPositionService = atpRankingPositionService;
    }

    public double prematchOddsCalculate(Event event) {
        Player playerOne = event.getPlayerOne();
        Player playerTwo = event.getPlayerTwo();

        int pointsDifference = getAtpPoint(playerOne) - getAtpPoint(playerTwo);
        int standingIntervalDifference = getPointsInterval(playerOne) - getPointsInterval(playerTwo);
        double ageDifference = getPlayerAge(playerOne) - getPlayerAge(playerTwo);
        int homePlayFactorDifference = getHomePlayFactor(playerOne, event) - getHomePlayFactor(playerTwo, event);

        return 2.30 + 0.0002 * pointsDifference + 0.187 * standingIntervalDifference - 0.064 * ageDifference + 0.497 * homePlayFactorDifference;
    }

    private int getHomePlayFactor(Player player, Event event) {
        return player.getCountryCode().equals(event.getCountry()) ? 1 : 0;
    }

    private double getPlayerAge(Player player) {
        return (Period.between(player.getBirthday(), LocalDate.now()).toTotalMonths())/12.0;
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
