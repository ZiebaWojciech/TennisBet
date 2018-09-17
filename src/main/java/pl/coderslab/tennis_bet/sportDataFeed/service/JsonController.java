package pl.coderslab.tennis_bet.sportDataFeed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Player;
import pl.coderslab.tennis_bet.sportDataFeed.repository.EventRepository;
import pl.coderslab.tennis_bet.sportDataFeed.repository.PlayerRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JsonController {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping("/get")
    public void getCountriesAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "http://localhost:5000/api/events";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Event[]> responseCountries = restTemplate.getForEntity(
                url, Event[].class);
        Event[] events = responseCountries.getBody();
        for (Event event : events) {
            eventRepository.save(event);
            System.out.println(event.getPlayerOne().getName() + " player one");
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
