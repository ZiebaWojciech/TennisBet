package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;

@Controller
public class HomepageController {
    private final TennisMatchService tennisMatchService;

    @Autowired
    public HomepageController(TennisMatchService tennisMatchService) {
        this.tennisMatchService = tennisMatchService;
    }

    @RequestMapping(path = "/homepage", method = RequestMethod.GET)
    public String homepage(Model model){
        model.addAttribute("upcomingTennisMatches", tennisMatchService.getUpcomingTennisMatches());
        return "homepage";
    }
}
