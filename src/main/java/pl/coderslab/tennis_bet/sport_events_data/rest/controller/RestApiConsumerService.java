package pl.coderslab.tennis_bet.sport_events_data.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.repository.TennisMatchRepository;
import pl.coderslab.tennis_bet.betting_site.service.OddsService;
import pl.coderslab.tennis_bet.betting_site.service.ResultService;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;
import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;
import pl.coderslab.tennis_bet.sport_events_data.repository.PlayerRepository;
import pl.coderslab.tennis_bet.sport_events_data.service.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class RestApiConsumerService {
    @Autowired
    TennisMatchRepository tennisMatchRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerDTOService playerDTOService;
    @Autowired
    OddsService oddsService;
    @Autowired
    TennisMatchService tennisMatchService;
    @Autowired
    TennisMatchDTOService tennisMatchDTOService;
    @Autowired
    ResultService resultService;
    @Autowired
    ResultDTOService resultDtoService;
    @Autowired
    TennisSetDtoService tennisSetDtoService;

    //TODO make automatic sending in API and receiving here (Karol link)
    //TODO controller has to return view, do it as service?
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
    @ResponseBody
    public String getPlayersFromApi() {
        String url = "http://localhost:5000/api/players";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlayerDTO[]> responseEntity = restTemplate.getForEntity(
                url, PlayerDTO[].class);
        PlayerDTO[] playerDtos = responseEntity.getBody();
        List<Player> allPlayers = playerService.getAll();
        for (PlayerDTO playerDto : playerDtos) {
            if(playerService.unknownPlayer(playerDto, allPlayers)){
                playerService.save(playerDTOService.convertPlayerDtoToEntity(playerDto));
                System.out.println("new player saved");
            }
        }
        return "yes";
    }

    @RequestMapping("/player/{id}")
    @ResponseBody
    public String getSinglePlayerFromApi(@PathVariable int id) {
        String url = "http://localhost:5000/api/players/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlayerDTO[]> responseEntity = restTemplate.getForEntity(url, PlayerDTO[].class);
        PlayerDTO[] playerDtos = responseEntity.getBody();
        List<Player> allPlayers = playerService.getAll();
        for (PlayerDTO playerDto : playerDtos) {
            if(playerService.unknownPlayer(playerDto, allPlayers)){
                playerService.save(playerDTOService.convertPlayerDtoToEntity(playerDto));
            }
        }
        return "yes";
    }

    @RequestMapping("/get-events")
    @ResponseBody
    public String getEventsFromApi() {
        String url = "http://localhost:5000/api/events";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TennisMatchDTO[]> responseEntity = restTemplate.getForEntity(
                url, TennisMatchDTO[].class);
        TennisMatchDTO[] tennisMatchDtos = responseEntity.getBody();
        List<TennisMatch> allTennisMatches = tennisMatchService.getAll();
        List<Player> allPlayers = playerService.getAll();
        for (TennisMatchDTO tennisMatchDTO : tennisMatchDtos) {
            if(tennisMatchService.unknownTennisMatch(tennisMatchDTO, allTennisMatches)){
                createNewTennisMatchFromApi(allPlayers, tennisMatchDTO);
            }
        }
        return "Events updated";
    }

    public void createNewTennisMatchFromApi(List<Player> allPlayers, TennisMatchDTO tennisMatchDTO) {
        TennisMatch newTennisMatch = tennisMatchDTOService.convertTennisMatchDtoToEntity(tennisMatchDTO);
        if(playerService.unknownPlayer(tennisMatchDTO.getPlayerOneDto(), allPlayers)){
            newTennisMatch.setPlayerOne(playerService.save(playerDTOService.convertPlayerDtoToEntity(tennisMatchDTO.getPlayerOneDto())));
        } else {
            newTennisMatch.setPlayerOne(playerService.playerByPlayerDTO(tennisMatchDTO.getPlayerOneDto()));
        }
        if(playerService.unknownPlayer(tennisMatchDTO.getPlayerTwoDto(), allPlayers)){
            newTennisMatch.setPlayerTwo(playerService.save(playerDTOService.convertPlayerDtoToEntity(tennisMatchDTO.getPlayerTwoDto())));
        } else {
            newTennisMatch.setPlayerTwo(playerService.playerByPlayerDTO(tennisMatchDTO.getPlayerTwoDto()));
        }
        BigDecimal[] odds = oddsService.prematchOddsCalculate(newTennisMatch);
        newTennisMatch.setOdds(new Odd(newTennisMatch, odds[0], odds[1]));
        tennisMatchService.save(newTennisMatch);
        System.out.println("new event saved");
    }

    @RequestMapping("/get-results-in-progress")
    @ResponseBody
    public String getResultsInProgressFromApi() {
        String url = "http://localhost:5000/api/results/in-progress";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResultDTO[]> responseEntity = restTemplate.getForEntity(url, ResultDTO[].class);
        ResultDTO[] resultDtos = responseEntity.getBody();
        List<TennisMatch> allTennisMatches = tennisMatchService.getAll();
        List<Player> allPlayers = playerService.getAll();
        for (ResultDTO resultDTO : resultDtos) {
            if(tennisMatchService.unknownTennisMatch(resultDTO.getTennisMatchDto(), allTennisMatches)){
                createNewTennisMatchFromApi(allPlayers, resultDTO.getTennisMatchDto());
            } else {
                TennisMatch tennisMatch = tennisMatchService.getTennisMatchByTennisMatchDTO(resultDTO.getTennisMatchDto());
                if(tennisMatch.getResult() != null){
                    //TODO instead of deleting result every element of it shall be checked and only new values added; without it IDs will reach max very fast
                    //TODO undone due to time limit
                    resultService.deleteById(tennisMatch.getResult().getId());
                }
                Result result = resultDtoService.convertResultDtoToEntity(resultDTO);
                result.setTennisMatch(tennisMatch);
                resultService.save(result);

                if(resultDTO.getTennisMatchDto().getStatus() != tennisMatch.getStatus()){
                    tennisMatch.setStatus(resultDTO.getTennisMatchDto().getStatus());
                    tennisMatch.setResult(result);
                    tennisMatchService.save(tennisMatch);
                }
            }

        }
        return "Result updated";
    }
}
