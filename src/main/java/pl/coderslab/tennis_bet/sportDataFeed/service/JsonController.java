package pl.coderslab.tennis_bet.sportDataFeed.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JsonController {
    @RequestMapping("/get")
    public void getCountriesAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "http://localhost:5000/api/events";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Event[]> responseCountries = restTemplate.getForEntity(
                url, Event[].class);
        Event[] events = responseCountries.getBody();
        for (Event event: events) {
            System.out.println(event.getPlayerOne().getName() + " player one");
        }
    }
}
