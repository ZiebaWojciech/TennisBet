package pl.coderslab.tennis_bet.betting_site.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
