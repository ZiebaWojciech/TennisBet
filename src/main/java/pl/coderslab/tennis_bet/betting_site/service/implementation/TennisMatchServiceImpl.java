package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.service.BetSelectionService;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.EventStatus;
import pl.coderslab.tennis_bet.betting_site.repository.TennisMatchRepository;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;

import java.util.List;

@Service
public class TennisMatchServiceImpl implements TennisMatchService {
    private final TennisMatchRepository tennisMatchRepository;
    private final BetSelectionService betSelectionService;

    @Autowired
    public TennisMatchServiceImpl(TennisMatchRepository tennisMatchRepository, BetSelectionService betSelectionService) {
        this.tennisMatchRepository = tennisMatchRepository;
        this.betSelectionService = betSelectionService;
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
        if(isEventStatusChanged(tennisMatch)){
            betSelectionService.resolveBetAfterEventStatusChange(tennisMatch);
        }
        return tennisMatchRepository.save(tennisMatch);
    }

    @Override
    public boolean isEventStatusChanged(TennisMatch updatedTennisMatch) {
        TennisMatch currentTennisMatch = getOne(updatedTennisMatch.getId());
        if(currentTennisMatch != null){
            return currentTennisMatch.getStatus().equals(updatedTennisMatch.getStatus());
        }
        return false;
    }

}
