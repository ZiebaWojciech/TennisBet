package pl.coderslab.tennis_bet.sportDataFeed.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sportDataFeed.entity.*;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.EventStatus;
import pl.coderslab.tennis_bet.sportDataFeed.repository.TennisMatchRepository;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisMatchService;

import java.util.List;

@Service
public class TennisMatchServiceImpl implements TennisMatchService {
    private final TennisMatchRepository tennisMatchRepository;


    @Autowired
    public TennisMatchServiceImpl(TennisMatchRepository tennisMatchRepository) {
        this.tennisMatchRepository = tennisMatchRepository;
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
        return tennisMatchRepository.save(tennisMatch);
    }

}
