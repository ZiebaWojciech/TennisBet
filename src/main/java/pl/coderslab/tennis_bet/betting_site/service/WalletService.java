package pl.coderslab.tennis_bet.betting_site.service;

import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.entity.Wallet;

import java.math.BigDecimal;

public interface WalletService {
    Wallet getOne(int id);
    Wallet getByUser(User user);

    void addToBalance(Wallet wallet, BigDecimal ammount);
    boolean deductFromBalance(Wallet wallet, BigDecimal ammount);

}
