package pl.coderslab.tennis_bet.service;

import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.entity.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    Wallet getOne(int id);
    Wallet getByUser(User user);

    void addToBalance(Wallet wallet, BigDecimal ammount);
    boolean deductFromBalance(Wallet wallet, BigDecimal ammount);

}
