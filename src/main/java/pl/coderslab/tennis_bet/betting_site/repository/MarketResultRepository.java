package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.betting_site.entity.MarketResult;
import pl.coderslab.tennis_bet.betting_site.entity.Role;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.RoleName;

public interface MarketResultRepository extends JpaRepository<MarketResult, Integer> {
}
