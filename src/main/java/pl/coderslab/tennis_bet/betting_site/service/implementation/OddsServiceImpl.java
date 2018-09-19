package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.repository.OddsRepository;
import pl.coderslab.tennis_bet.betting_site.service.OddsService;
import pl.coderslab.tennis_bet.sport_events_data.entity.AtpRankingPosition;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.service.AtpRankingPositionService;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class OddsServiceImpl implements OddsService {
    private final OddsRepository oddsRepository;
    private final AtpRankingPositionService atpRankingPositionService;
    private final TennisMatchService tennisMatchService;
    private final double SPORTBOOK_MARGIN_FACTOR = 1.08;

    @Autowired
    public OddsServiceImpl(OddsRepository oddsRepository, AtpRankingPositionService atpRankingPositionService, TennisMatchService tennisMatchService) {
        this.oddsRepository = oddsRepository;
        this.atpRankingPositionService = atpRankingPositionService;
        this.tennisMatchService = tennisMatchService;
    }

    @Override
    public Odd getOddsOfEvent(TennisMatch tennisMatch) {
        return oddsRepository.getAllByTennisMatch(tennisMatch);
    }

    @Scheduled(cron = "* * * ? * TUE")
    /**
     * Odds are recalculated every Tuesday as the new ATP ranking
     * is being announced every other Monday
     */
    @Override
    public void recalculatePrematchOdds() {
        List<TennisMatch> upcomingTennisMatches = tennisMatchService.getUpcomingTennisMatches();
        for(TennisMatch upcomingMatch : upcomingTennisMatches){
            BigDecimal[] odds = prematchOddsCalculate(upcomingMatch);
            upcomingMatch.getOdds().setPlayerOneWinningOdd(odds[0]);
            upcomingMatch.getOdds().setPlayerTwoWinningOdd(odds[1]);
            tennisMatchService.save(upcomingMatch);
        }
    }
    @Override
    public BigDecimal[] prematchOddsCalculate(TennisMatch tennisMatch) {
        Player playerOne = tennisMatch.getPlayerOne();
        Player playerTwo = tennisMatch.getPlayerTwo();

        int pointsDifference = getAtpPoint(playerOne) - getAtpPoint(playerTwo);
        int standingIntervalDifference = getPointsInterval(playerOne) - getPointsInterval(playerTwo);
        double ageDifference = getPlayerAge(playerOne) - getPlayerAge(playerTwo);
        int homePlayFactorDifference = getHomePlayFactor(playerOne, tennisMatch) - getHomePlayFactor(playerTwo, tennisMatch);

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

    private int getHomePlayFactor(Player player, TennisMatch tennisMatch) {
        return player.getCountryCode().equals(tennisMatch.getCountry()) ? 1 : 0;
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
