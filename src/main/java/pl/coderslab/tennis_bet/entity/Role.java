package pl.coderslab.tennis_bet.entity;

import lombok.Data;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleName name;
}
