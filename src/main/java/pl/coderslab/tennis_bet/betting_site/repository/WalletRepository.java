package pl.coderslab.tennis_bet.betting_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet getByUser(User user);
}
