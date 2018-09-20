package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
