package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.repository.ResultRepository;
import pl.coderslab.tennis_bet.betting_site.service.ResultService;
import pl.coderslab.tennis_bet.sport_events_data.service.TennisSetService;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public Result getOne(int id) {
        return resultRepository.getOne(id);
    }

    @Override
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public void delete(Result result) {
        resultRepository.delete(result);
    }

    @Override
    public void deleteById(int id) {
        resultRepository.deleteById(id);
    }

}
