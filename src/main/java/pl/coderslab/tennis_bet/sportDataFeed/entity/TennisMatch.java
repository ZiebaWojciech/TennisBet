package pl.coderslab.tennis_bet.sportDataFeed.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.Country;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.EventStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class TennisMatch {
    @Id
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeOfStart;
    @NotNull
    @ManyToOne
    private Player playerOne;
    @NotNull
    @ManyToOne
    private Player playerTwo;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Odd odds;

}
