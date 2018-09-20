package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.entity.Wallet;

import java.math.BigDecimal;

public interface WalletService {
    Wallet getOne(int id);
    Wallet getByUser(User user);
    Wallet save(Wallet wallet);

    void rechargeFunds(User user, BigDecimal amount);
    void cashOutTicket(User user, BigDecimal amount);
    Wallet addToBalance(Wallet wallet, BigDecimal amount);

    boolean payForTicket(User user, BigDecimal amount);
    boolean withdraw(User user, BigDecimal amount);

    Wallet deductFromBalance(Wallet wallet, BigDecimal amount);

}
