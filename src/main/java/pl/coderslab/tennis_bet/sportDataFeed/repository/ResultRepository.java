package pl.coderslab.tennis_bet.sportDataFeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result getByTennisMatch(TennisMatch tennisMatch);
}
