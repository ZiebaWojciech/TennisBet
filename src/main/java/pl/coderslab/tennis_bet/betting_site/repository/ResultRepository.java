package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result getByTennisMatch(TennisMatch tennisMatch);
}
