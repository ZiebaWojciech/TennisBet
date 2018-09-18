package pl.coderslab.tennis_bet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet getByUser(User user);
}
