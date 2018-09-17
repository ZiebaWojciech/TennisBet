package pl.coderslab.tennis_bet.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(max = 30)
    @Column(unique = true, length = 30)
    private String login;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String password;

    @NotEmpty
    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    private boolean active;
}
