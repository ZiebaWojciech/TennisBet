package pl.coderslab.tennis_bet.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.entity.MoneyTransaction;
import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.entity.Wallet;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;
import pl.coderslab.tennis_bet.entity.enumUtil.TransactionType;
import pl.coderslab.tennis_bet.repository.UserRepository;
import pl.coderslab.tennis_bet.repository.WalletRepository;
import pl.coderslab.tennis_bet.service.RoleService;
import pl.coderslab.tennis_bet.service.UserService;
import pl.coderslab.tennis_bet.service.WalletService;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.EventStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getOne(int id) {
        return walletRepository.getOne(id);
    }

    @Override
    public Wallet getByUser(User user) {
        return walletRepository.getByUser(user);
    }

    @Override
    public void addToBalance(Wallet wallet, BigDecimal amount) {
        synchronized (wallet) {
            amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
            wallet.setBalance(wallet.getBalance().add(amount));
            wallet.addMoneyTransaction(new MoneyTransaction(TransactionType.RECHARGE,LocalDateTime.now(), amount));
            walletRepository.save(wallet);
        }

    }

    @Override
    public boolean deductFromBalance(Wallet wallet, BigDecimal amount) {
        synchronized (wallet) {
            BigDecimal balance = wallet.getBalance();
            amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
            if(balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0){
                balance = balance.subtract(amount);
                wallet.setBalance(balance);
                wallet.addMoneyTransaction(new MoneyTransaction(TransactionType.WITHDRAW, LocalDateTime.now(), amount));
                walletRepository.save(wallet);
                return true;
            } else {
                return false;
            }

        }
    }
}
