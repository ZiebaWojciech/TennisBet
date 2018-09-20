package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.TennisGame;
import pl.coderslab.tennis_bet.sport_events_data.repository.TennisGameRepository;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisGameService;

import java.util.List;

@Service
public class TennisGameServiceImpl implements TennisGameService {
    private final TennisGameRepository tennisGameRepository;

    @Autowired
    public TennisGameServiceImpl(TennisGameRepository tennisGameRepository) {
        this.tennisGameRepository = tennisGameRepository;
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
