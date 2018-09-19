package pl.coderslab.tennis_bet.sport_events_data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "atp_ranking")
@Getter
@Setter
public class AtpRankingPosition {
    @Id
    private long id;
    @ManyToOne
    private Player player;
    private LocalDate date;
    private int points;
    private int standing;
}
