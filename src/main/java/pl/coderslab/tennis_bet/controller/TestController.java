package pl.coderslab.tennis_bet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.tennis_bet.service.OddsService;
import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;
import pl.coderslab.tennis_bet.sportDataFeed.service.EventService;

@Controller
public class TestController {
    @Autowired
    OddsService oddsService;
    @Autowired
    EventService eventService;

    @RequestMapping(path = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String test(@PathVariable int id){
        return "one" + String.valueOf(oddsService.prematchOddsCalculate(eventService.getOne(id))[0])+ " two " + String.valueOf(oddsService.prematchOddsCalculate(eventService.getOne(id))[1]);
    }
}