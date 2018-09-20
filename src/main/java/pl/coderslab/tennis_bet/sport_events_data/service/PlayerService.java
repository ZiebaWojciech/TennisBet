package pl.coderslab.tennis_bet.sport_events_data.service;

import pl.coderslab.tennis_bet.betting_site.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    Player getOne(int id);
    List<Player> getAll();

    Player save(Player player);

    boolean unknownPlayer(PlayerDTO checkedPlayer, List<Player> allPlayers);
    boolean isSamePlayer(PlayerDTO checkedPlayer, Player player);
    Player playerByPlayerDTO(PlayerDTO checkedPlayer);
    int playerHashCode(Player player);
}
