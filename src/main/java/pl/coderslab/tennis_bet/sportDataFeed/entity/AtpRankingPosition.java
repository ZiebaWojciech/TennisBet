package pl.coderslab.tennis_bet.sportDataFeed.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "atp_ranking")
@Data
public class AtpRankingPosition {
    @Id
    private long id;
    @ManyToOne
    private Player player;
    private LocalDate date;
    private int points;
    private int standing;
}
