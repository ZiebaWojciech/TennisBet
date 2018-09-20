package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.MarketResult;
import pl.coderslab.tennis_bet.betting_site.repository.MarketResultRepository;
import pl.coderslab.tennis_bet.betting_site.service.MarketResultService;

@Service
public class MarketResultServiceImpl implements MarketResultService {
    @Autowired
    MarketResultRepository marketResultRepository;

    @Override
    public MarketResult save(MarketResult marketResult) {
        return marketResultRepository.save(marketResult);
    }
}
