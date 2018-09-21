package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.MarketResult;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionType;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.EventStatus;
import pl.coderslab.tennis_bet.betting_site.repository.TennisMatchRepository;
import pl.coderslab.tennis_bet.betting_site.service.BetSelectionService;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;
import pl.coderslab.tennis_bet.sport_events_data.service.PlayerService;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisMatchDTOService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TennisMatchServiceImpl implements TennisMatchService {
    private final TennisMatchRepository tennisMatchRepository;
    private final BetSelectionService betSelectionService;
    private final PlayerService playerService;
    private final TennisMatchDTOService tennisMatchDTOService;

    @Autowired
    public TennisMatchServiceImpl(TennisMatchRepository tennisMatchRepository, BetSelectionService betSelectionService, PlayerService playerService, TennisMatchDTOService tennisMatchDTOService) {
        this.tennisMatchRepository = tennisMatchRepository;
        this.betSelectionService = betSelectionService;
        this.playerService = playerService;
        this.tennisMatchDTOService = tennisMatchDTOService;
    }

    @Override
    public TennisMatch getOne(int id) {
        return tennisMatchRepository.getOne(id);
    }

    @Override
    public List<TennisMatch> getAll() {
        return tennisMatchRepository.findAll();
    }

    @Override
    public List<TennisMatch> getUpcomingTennisMatches() {
        return tennisMatchRepository.getAllByStatusEqualsOrderByTimeOfStartDesc(EventStatus.SCHEDULED);
    }

    @Override
    public TennisMatch save(TennisMatch tennisMatch) {
        //TODO check why this condition does not work properly (Lazy initializer? The entitiy is not null, but all field are)
//        if (isEventStatusChanged(tennisMatch)) {
        if (tennisMatch.getStatus() == EventStatus.COMPLETED) {
            tennisMatch = setEventMarketResults(tennisMatch);
        }
        betSelectionService.resolveBetAfterEventStatusChange(tennisMatch);
        return tennisMatchRepository.save(tennisMatch);
    }

    @Override
    public boolean isEventStatusChanged(TennisMatch updatedTennisMatch) {
        if (updatedTennisMatch.getId() == 0) {
            return false;
        }
        TennisMatch currentTennisMatch = getOne(updatedTennisMatch.getId());
        if (currentTennisMatch != null) {
            return currentTennisMatch.getStatus() == updatedTennisMatch.getStatus();
        }
        return false;
    }

    @Override
    public TennisMatch setEventMarketResults(TennisMatch tennisMatch) {
        List<MarketResult> marketResults = new ArrayList<>();
        if (tennisMatch.getResult().getWinner().getId() == tennisMatch.getPlayerOne().getId()) {
            marketResults.add(new MarketResult(BetSelectionType.PLAYER_ONE_WINS, tennisMatch.getResult()));
        }

        if (tennisMatch.getResult().getWinner().getId() == tennisMatch.getPlayerTwo().getId()) {
            marketResults.add(new MarketResult(BetSelectionType.PLAYER_TWO_WINS, tennisMatch.getResult()));
        }
        tennisMatch.getResult().setMarketResults(marketResults);
        return tennisMatch;
    }

    @Override
    public int tennisMatchHashCode(TennisMatch tennisMatch) {
        return Objects.hash(tennisMatch.getCountry(), tennisMatch.getTimeOfStart(), playerService.playerHashCode(tennisMatch.getPlayerOne()), playerService.playerHashCode(tennisMatch.getPlayerTwo()));
    }

    @Override
    public boolean unknownTennisMatch(TennisMatchDTO checkedTennisMatch, List<TennisMatch> tennisMatches) {
        return tennisMatches.stream().noneMatch(v -> isSameTennisMatch(checkedTennisMatch, v));
    }

    @Override
    public boolean isSameTennisMatch(TennisMatchDTO checkedTennisMatch, TennisMatch tennisMatch) {
        return tennisMatchDTOService.tennisMatchDtoHashCode(checkedTennisMatch) == tennisMatchHashCode(tennisMatch);
    }

    @Override
    public TennisMatch getTennisMatchByTennisMatchDTO(TennisMatchDTO tennisMatchDTO) {
        List<TennisMatch> allTennisMatches = getAll();
        return allTennisMatches.stream().filter(v -> tennisMatchHashCode(v) == tennisMatchDTOService.tennisMatchDtoHashCode(tennisMatchDTO)).findFirst().get();

    }

    @Override
    public boolean hasEventStarted(TennisMatch tennisMatch) {
        return tennisMatch.getTimeOfStart().compareTo(LocalDateTime.now()) < 0;
    }


}
