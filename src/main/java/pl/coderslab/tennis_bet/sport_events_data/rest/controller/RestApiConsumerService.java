package pl.coderslab.tennis_bet.sport_events_data.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.service.OddsService;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;
import pl.coderslab.tennis_bet.betting_site.repository.TennisMatchRepository;
import pl.coderslab.tennis_bet.sport_events_data.repository.PlayerRepository;

import java.math.BigDecimal;

@Controller
public class RestApiConsumerService {
    @Autowired
    TennisMatchRepository tennisMatchRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    OddsService oddsService;
    @Autowired
    TennisMatchService tennisMatchService;

    //TODO make automatic sending in API and receiving here (Karol link)
    //TODO controller has to return view, do it as service?
    @RequestMapping("/get-events")
    public void getTennisMatch() {
        String tennisApiUrl = "http://localhost:5000/api/events";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TennisMatch[]> responseTennisMatch = restTemplate.getForEntity(
                tennisApiUrl, TennisMatch[].class);

        TennisMatch[] tennisMatches = responseTennisMatch.getBody();
        for (TennisMatch tennisMatch : tennisMatches) {
            BigDecimal[] odds = oddsService.prematchOddsCalculate(tennisMatch);
            tennisMatch.setOdds(new Odd(tennisMatch, odds[0], odds[1]));
            tennisMatchService.save(tennisMatch);
            //TODO check if odds already exist if match exist (same to matches?)
        }
    }
//
//    @RequestMapping("/get-countries")
//    public void getCountriesAction() {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<TennisMatch[]> responseCountries = restTemplate.getForEntity(
//                tennisApiUrl, TennisMatch[].class);
//
//        TennisMatch[] tennisMatches = responseCountries.getBody();
//        for (TennisMatch tennisMatch : tennisMatches) {
//            tennisMatchRepository.save(tennisMatch);
//        }
//    }

    @RequestMapping("/get-players")
    public void getPlayers() {
        String url = "http://localhost:5000/api/players";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Player[]> responseEntity = restTemplate.getForEntity(
                url, Player[].class);
        Player[] players = responseEntity.getBody();
        for (Player player : players) {
            playerRepository.save(player);
        }
    }

    @RequestMapping("/player/{id}")
    public void getPlayer(@PathVariable int id) {
        String url = "http://localhost:5000/api/players/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Player> responseEntity = restTemplate.getForEntity(url, Player.class);
        Player player = responseEntity.getBody();
        playerRepository.save(player);
    }
}
