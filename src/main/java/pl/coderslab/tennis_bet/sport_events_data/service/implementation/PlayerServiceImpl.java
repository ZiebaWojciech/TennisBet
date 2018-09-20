package pl.coderslab.tennis_bet.sport_events_data.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.betting_site.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;
import pl.coderslab.tennis_bet.sport_events_data.repository.PlayerRepository;
import pl.coderslab.tennis_bet.sport_events_data.service.PlayerDTOService;
import pl.coderslab.tennis_bet.sport_events_data.service.PlayerService;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerDTOService playerDTOService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerDTOService playerDTOService) {
        this.playerRepository = playerRepository;
        this.playerDTOService = playerDTOService;
    }

    @Override
    public Player getOne(int id) {
        return playerRepository.getOne(id);
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public boolean unknownPlayer(PlayerDTO checkedPlayer, List<Player> allPlayers) {
//        return
        return allPlayers.stream().noneMatch(v->isSamePlayer(checkedPlayer, v));
    }

    @Override
    public boolean isSamePlayer(PlayerDTO checkedPlayer, Player player) {
        return playerDTOService.playerDtoHashCode(checkedPlayer) == playerHashCode(player);
    }

    @Override
    public Player playerByPlayerDTO(PlayerDTO checkedPlayer) {
        List<Player> allPlayers = getAll();
        return allPlayers.stream().filter(v->playerHashCode(v) == playerDTOService.playerDtoHashCode(checkedPlayer)).findFirst().get();
    }


    @Override
    public int playerHashCode(Player player) {
        return Objects.hash(player.getName(), player.getSurname(),player.getCountry(), player.getBirthday());
    }


}
