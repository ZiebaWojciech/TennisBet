package pl.coderslab.tennis_bet.sportDataFeed.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sportDataFeed.entity.*;
import pl.coderslab.tennis_bet.sportDataFeed.repository.TennisGameRepository;
import pl.coderslab.tennis_bet.sportDataFeed.service.ResultService;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisGameService;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisSetService;

import java.util.List;

@Service
public class TennisGameServiceImpl implements TennisGameService {
    private final TennisGameRepository tennisGameRepository;
    private TennisSetService tennisSetService;
    private ResultService resultService;

    @Autowired
    public TennisGameServiceImpl(TennisGameRepository tennisGameRepository) {
        this.tennisGameRepository = tennisGameRepository;
    }
    @Autowired
    public void setTennisSetService(TennisSetService tennisSetService) {
        this.tennisSetService = tennisSetService;
    }
    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public TennisGame getOne(int id) {
        return tennisGameRepository.getOne(id);
    }

    @Override
    public List<TennisGame> getAll() {
        return tennisGameRepository.findAll();
    }

    @Override
    public TennisGame save(TennisGame tennisGame) {
        return tennisGameRepository.save(tennisGame);
    }

}
