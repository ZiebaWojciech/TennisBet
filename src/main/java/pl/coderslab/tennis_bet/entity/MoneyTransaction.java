package pl.coderslab.tennis_bet.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import pl.coderslab.tennis_bet.entity.enumUtil.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @CreationTimestamp
    private LocalDateTime time;
    private BigDecimal value;
}
