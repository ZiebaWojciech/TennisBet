package pl.coderslab.tennis_bet.betting_site.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.tennis_bet.betting_site.entity.MoneyTransaction;
import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.entity.Wallet;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.TransactionType;
import pl.coderslab.tennis_bet.betting_site.repository.WalletRepository;
import pl.coderslab.tennis_bet.betting_site.service.implementation.WalletServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WalletServiceTest {
    private WalletService service;
    private WalletRepository walletRepository;
    @Before
    public void setUp(){
        walletRepository = mock(WalletRepository.class);
        service = new WalletServiceImpl(walletRepository);
    }
    @Test
    public void getOne() {
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(12));
        wallet.setUser(new User());
        wallet.setId(1);

        when(walletRepository.getOne(1)).thenReturn(wallet);

        Wallet actual = service.getOne(1);
        assertEquals(actual.getId(), 1);
        assertEquals(actual.getBalance(), BigDecimal.valueOf(12));
    }

    @Test
    public void addToBalance() {
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(12));
        User user = new User();
        wallet.setUser(user);
        wallet.setId(1);

        when(service.save(wallet)).thenReturn(wallet);


        Wallet actual = service.addToBalance(wallet, BigDecimal.valueOf(8));

        assertEquals(actual.getBalance(), BigDecimal.valueOf(20).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void deductFromBalance() {
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(12));
        User user = new User();
        wallet.setUser(user);

        when(service.save(wallet)).thenReturn(wallet);

        Wallet actual = service.deductFromBalance(wallet, BigDecimal.valueOf(8));
        assertEquals(actual.getBalance(), BigDecimal.valueOf(4).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}