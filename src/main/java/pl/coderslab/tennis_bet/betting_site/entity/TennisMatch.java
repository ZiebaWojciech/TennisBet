package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.Country;
import pl.coderslab.tennis_bet.sport_events_data.entity.enumUtil.EventStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
    @OneToOne(mappedBy = "tennisMatch")
    private Result result;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Odd odds;

    @OneToMany(mappedBy = "tennisMatch")
    private List<BetSelection> betSelectionsRelatedToMatch = new ArrayList<>();
}
