package pl.coderslab.tennis_bet.entity;

import lombok.Data;
import pl.coderslab.tennis_bet.entity.enumUtil.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class MoneyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Wallet wallet;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private BigDecimal value;
}
