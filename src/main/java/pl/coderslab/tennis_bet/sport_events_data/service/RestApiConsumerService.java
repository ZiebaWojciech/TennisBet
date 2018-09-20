package pl.coderslab.tennis_bet.sport_events_data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.tennis_bet.betting_site.entity.Odd;
import pl.coderslab.tennis_bet.betting_site.entity.Result;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.service.OddsService;
import pl.coderslab.tennis_bet.betting_site.service.ResultService;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;
import pl.coderslab.tennis_bet.sport_events_data.dto.PlayerDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.ResultDTO;
import pl.coderslab.tennis_bet.sport_events_data.dto.TennisMatchDTO;
import pl.coderslab.tennis_bet.sport_events_data.entity.Player;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RestApiConsumerService {
    private final PlayerService playerService;
    private final PlayerDTOService playerDTOService;
    private final OddsService oddsService;
    private final TennisMatchService tennisMatchService;
    private final TennisMatchDTOService tennisMatchDTOService;
    private final ResultService resultService;
    private final ResultDTOService resultDtoService;

    @Autowired
    public RestApiConsumerService(PlayerService playerService, PlayerDTOService playerDTOService, OddsService oddsService, TennisMatchService tennisMatchService, TennisMatchDTOService tennisMatchDTOService, ResultService resultService, ResultDTOService resultDtoService) {
        this.playerService = playerService;
        this.playerDTOService = playerDTOService;
        this.oddsService = oddsService;
        this.tennisMatchService = tennisMatchService;
        this.tennisMatchDTOService = tennisMatchDTOService;
        this.resultService = resultService;
        this.resultDtoService = resultDtoService;
    }

    //    @RequestMapping("/get-players")
    public void getPlayersFromApi() {
        String url = "http://localhost:5000/api/players";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlayerDTO[]> responseEntity = restTemplate.getForEntity(
                url, PlayerDTO[].class);
        PlayerDTO[] playerDtos = responseEntity.getBody();
        List<Player> allPlayers = playerService.getAll();
        for (PlayerDTO playerDto : playerDtos) {
            if (playerService.unknownPlayer(playerDto, allPlayers)) {
                playerService.save(playerDTOService.convertPlayerDtoToEntity(playerDto));
            }
        }
    }

    //    @RequestMapping("/player/{id}")
    public void getSinglePlayerFromApi(@PathVariable int id) {
        String url = "http://localhost:5000/api/players/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlayerDTO[]> responseEntity = restTemplate.getForEntity(url, PlayerDTO[].class);
        PlayerDTO[] playerDtos = responseEntity.getBody();
        List<Player> allPlayers = playerService.getAll();
        for (PlayerDTO playerDto : playerDtos) {
            if (playerService.unknownPlayer(playerDto, allPlayers)) {
                playerService.save(playerDTOService.convertPlayerDtoToEntity(playerDto));
            }
        }
    }

    //    @RequestMapping("/get-events")
    @Scheduled(fixedRate = 5000)
    public void getEventsFromApi() {
        String url = "http://localhost:5000/api/events";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TennisMatchDTO[]> responseEntity = restTemplate.getForEntity(
                url, TennisMatchDTO[].class);
        TennisMatchDTO[] tennisMatchDtos = responseEntity.getBody();
        List<TennisMatch> allTennisMatches = tennisMatchService.getAll();
        List<Player> allPlayers = playerService.getAll();
        for (TennisMatchDTO tennisMatchDTO : tennisMatchDtos) {
            if (tennisMatchService.unknownTennisMatch(tennisMatchDTO, allTennisMatches)) {
                createNewTennisMatchFromApi(allPlayers, tennisMatchDTO);
            }
        }
    }

    public void createNewTennisMatchFromApi(List<Player> allPlayers, TennisMatchDTO tennisMatchDTO) {
        TennisMatch newTennisMatch = tennisMatchDTOService.convertTennisMatchDtoToEntity(tennisMatchDTO);
        if (playerService.unknownPlayer(tennisMatchDTO.getPlayerOneDto(), allPlayers)) {
            newTennisMatch.setPlayerOne(playerService.save(playerDTOService.convertPlayerDtoToEntity(tennisMatchDTO.getPlayerOneDto())));
        } else {
            newTennisMatch.setPlayerOne(playerService.playerByPlayerDTO(tennisMatchDTO.getPlayerOneDto()));
        }
        if (playerService.unknownPlayer(tennisMatchDTO.getPlayerTwoDto(), allPlayers)) {
            newTennisMatch.setPlayerTwo(playerService.save(playerDTOService.convertPlayerDtoToEntity(tennisMatchDTO.getPlayerTwoDto())));
        } else {
            newTennisMatch.setPlayerTwo(playerService.playerByPlayerDTO(tennisMatchDTO.getPlayerTwoDto()));
        }
        BigDecimal[] odds = oddsService.prematchOddsCalculate(newTennisMatch);
        newTennisMatch.setOdds(new Odd(newTennisMatch, odds[0], odds[1]));
        tennisMatchService.save(newTennisMatch);
    }

    //    @RequestMapping("/get-results-in-progress")
    @Scheduled(fixedRate = 5000L)
    public void getResultsInProgressFromApi() {
        String url = "http://localhost:5000/api/results/in-progress";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResultDTO[]> responseEntity = restTemplate.getForEntity(url, ResultDTO[].class);
        ResultDTO[] resultDtos = responseEntity.getBody();
        List<TennisMatch> allTennisMatches = tennisMatchService.getAll();
        List<Player> allPlayers = playerService.getAll();
        for (ResultDTO resultDTO : resultDtos) {
            if (tennisMatchService.unknownTennisMatch(resultDTO.getTennisMatchDto(), allTennisMatches)) {
                createNewTennisMatchFromApi(allPlayers, resultDTO.getTennisMatchDto());
            } else {
                TennisMatch tennisMatch = tennisMatchService.getTennisMatchByTennisMatchDTO(resultDTO.getTennisMatchDto());
                if (tennisMatch.getResult() != null) {
                    //TODO instead of deleting result every element of it shall be checked and only new values added; without it IDs will reach max very fast
                    //TODO undone due to time limit
                    resultService.deleteById(tennisMatch.getResult().getId());
                }
                Result result = resultDtoService.convertResultDtoToEntity(resultDTO);
                result.setTennisMatch(tennisMatch);
                resultService.save(result);

                if (resultDTO.getTennisMatchDto().getStatus() != tennisMatch.getStatus()) {
                    tennisMatch.setStatus(resultDTO.getTennisMatchDto().getStatus());
                    tennisMatch.setResult(result);
                    tennisMatchService.save(tennisMatch);
                }
            }

        }
    }
}
