package pl.coderslab.tennis_bet.sportDataFeed.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Result;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisSet;
import pl.coderslab.tennis_bet.sportDataFeed.repository.TennisSetRepository;
import pl.coderslab.tennis_bet.sportDataFeed.service.ResultService;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisGameService;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisSetService;

import java.util.List;

@Service
public class TennisSetServiceImpl implements TennisSetService {
    private final TennisSetRepository tennisSetRepository;

    private ResultService resultService;

    @Autowired
    public TennisSetServiceImpl(TennisSetRepository tennisSetRepository) {
        this.tennisSetRepository = tennisSetRepository;

    }

    @Autowired
    public void setTennisGameService(TennisGameService tennisGameService) {
        this.tennisGameService = tennisGameService;
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }


    @Override
    public TennisSet getOne(int id) {
        return tennisSetRepository.getOne(id);
    }

    @Override
    public List<TennisSet> getAll() {
        return tennisSetRepository.findAll();
    }

    @Override
    public TennisSet save(TennisSet tennisSet) {
        return tennisSetRepository.save(tennisSet);
    }
    @Override
    public long countGamesWonByPlayerOne(Result result) {
        return resultService.getCurrentSet(result).getGames().stream()
                .filter(game -> result.getEvent().getPlayerOne().equals(game.getTennisGameWinner()))
                .count();
    }
    @Override
    public long countGamesWonByPlayerTwo(Result result) {
        return resultService.getCurrentSet(result).getGames().stream()
                .filter(game -> result.getEvent().getPlayerTwo().equals(game.getTennisGameWinner()))
                .count();
    }

}
