package pl.coderslab.tennis_bet.sportDataFeed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.tennis_bet.sportDataFeed.entity.TennisMatch;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.repository.TennisMatchRepository;
import pl.coderslab.tennis_bet.sportDataFeed.repository.PlayerRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JsonController {
    @Autowired
    TennisMatchRepository tennisMatchRepository;
    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping("/get")
    public void getCountriesAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "http://localhost:5000/api/tennisMatches";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TennisMatch[]> responseCountries = restTemplate.getForEntity(
                url, TennisMatch[].class);
        TennisMatch[] tennisMatches = responseCountries.getBody();
        for (TennisMatch tennisMatch : tennisMatches) {
            tennisMatchRepository.save(tennisMatch);
            System.out.println(tennisMatch.getPlayerOne().getName() + " player one");
        }
    }

    @RequestMapping("/get-players")
    public void getPlayers() {
        String url = "http://localhost:5000/api/players";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Player[]> responseEntity = restTemplate.getForEntity(
                url, Player[].class);
        Player[] players = responseEntity.getBody();
        for (Player player : players) {
            playerRepository.save(player);
            System.out.println(player.getName() + " player one");
        }
    }

    @RequestMapping("/player/{id}")
    public void getPlayer(@PathVariable int id){
        String url = "http://localhost:5000/api/players/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Player> responseEntity = restTemplate.getForEntity(url, Player.class);
        Player player = responseEntity.getBody();
        playerRepository.save(player);
        System.out.println(player.getName() + " player one");
    }
}
