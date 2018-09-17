package pl.coderslab.tennis_bet.sportDataFeed.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;

import java.util.List;

public interface PlayerService {
    Player getOne(int id);
    List<Player> getAll();

    Player save(Player player);

}
