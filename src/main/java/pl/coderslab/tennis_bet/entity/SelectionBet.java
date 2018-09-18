package pl.coderslab.tennis_bet.entity;

import lombok.Data;
import pl.coderslab.tennis_bet.entity.enumUtil.SelectionType;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class SelectionBet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Event event;
    @NotNull
    private SelectionType selectionType;
    @NotNull
    private BigDecimal odds;
}
