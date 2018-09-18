package pl.coderslab.tennis_bet.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private  User user;

    private BigDecimal balance = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<MoneyTransaction> moneyTransactions = new ArrayList<>();

    public void addMoneyTransaction(MoneyTransaction moneyTransaction){
        this.moneyTransactions.add(moneyTransaction);
    }
}
