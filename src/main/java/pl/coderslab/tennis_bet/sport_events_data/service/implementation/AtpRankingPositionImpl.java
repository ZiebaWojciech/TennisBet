package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.sport_events_data.entity.AtpRankingPosition;
import pl.coderslab.tennis_bet.sport_events_data.repository.AtpRankingPositionRepository;
import pl.coderslab.tennis_bet.sport_events_data.service.AtpRankingPositionService;

import java.time.LocalDate;
import java.util.List;

@Service
public class AtpRankingPositionImpl implements AtpRankingPositionService {
    final
    AtpRankingPositionRepository atpRankingPositionRepository;

    @Autowired
    public AtpRankingPositionImpl(AtpRankingPositionRepository atpRankingPositionRepository) {
        this.atpRankingPositionRepository = atpRankingPositionRepository;
    }

    @Override
    public AtpRankingPosition getOne(int id) {
        return atpRankingPositionRepository.getOne(id);
    }

    @Override
    public List<AtpRankingPosition> getAllByPlayerId(int id) {
        return atpRankingPositionRepository.getAllByPlayerId(id);
    }

    @Override
    public List<AtpRankingPosition> getAllByTime(LocalDate date) {
        return atpRankingPositionRepository.getAllByDate(date);
    }

    @Override
    public AtpRankingPosition save(AtpRankingPosition atpRankingPosition) {
        return atpRankingPositionRepository.save(atpRankingPosition);
    }
}
