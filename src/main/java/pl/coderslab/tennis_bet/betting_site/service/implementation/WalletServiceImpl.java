package pl.coderslab.tennis_bet.betting_site.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.MoneyTransaction;
import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.entity.Wallet;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.TransactionType;
import pl.coderslab.tennis_bet.betting_site.repository.WalletRepository;
import pl.coderslab.tennis_bet.betting_site.service.WalletService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void rechargeFunds(User user, BigDecimal amount) {
        Wallet wallet = user.getWallet();
        wallet = addToBalance(wallet, amount);
        wallet.addMoneyTransaction(new MoneyTransaction(TransactionType.RECHARGE, LocalDateTime.now(), amount, wallet));
        user.setWallet(save(wallet));
    }

    @Override
    public void cashOutTicket(User user, BigDecimal amount) {
        Wallet wallet = user.getWallet();
        wallet = addToBalance(wallet, amount);
        wallet.addMoneyTransaction(new MoneyTransaction(TransactionType.CASH_OUT, LocalDateTime.now(), amount, wallet));
        user.setWallet(save(wallet));
    }

    @Override
    public Wallet addToBalance(Wallet wallet, BigDecimal amount) {
        synchronized (wallet) {
            amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
            wallet.setBalance(wallet.getBalance().add(amount));
            return save(wallet);
        }

    }

    @Override
    public boolean payForTicket(User user, BigDecimal amount) {
        Wallet wallet = user.getWallet();
        wallet = deductFromBalance(wallet, amount);
        if (wallet == null) {
            return false;
        }
        wallet.addMoneyTransaction(new MoneyTransaction(TransactionType.PAY_FOR_TICKET, LocalDateTime.now(), amount, wallet));
        user.setWallet(save(wallet));
        return true;
    }

    @Override
    public boolean withdraw(User user, BigDecimal amount) {
        Wallet wallet = user.getWallet();
        wallet = deductFromBalance(wallet, amount);
        if (wallet == null) {
            return false;
        }
        wallet.addMoneyTransaction(new MoneyTransaction(TransactionType.WITHDRAWAL, LocalDateTime.now(), amount, wallet));
        user.setWallet(save(wallet));
        return true;
    }

    @Override
    public Wallet deductFromBalance(Wallet wallet, BigDecimal amount) {
        synchronized (wallet) {
            BigDecimal balance = wallet.getBalance();
            amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
            if (balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
                balance = balance.subtract(amount);
                wallet.setBalance(balance);
                return save(wallet);
            }
        }
        return null;
    }
}
