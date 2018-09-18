package pl.coderslab.tennis_bet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennis_bet.sportDataFeed.service.EventService;

@Controller
@RequestMapping(path = "/homepage")
public class HomepageController {
    @Autowired
    EventService eventService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String homepage(Model model){
        model.addAttribute("upcomingEvents", eventService.getUpcomingEvents());
        return "homepage";
    }
}
