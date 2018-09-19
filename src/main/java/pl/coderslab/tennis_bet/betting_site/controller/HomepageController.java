package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;

@Controller
@RequestMapping(path = "/homepage")
public class HomepageController {
    @Autowired
    TennisMatchService tennisMatchService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String homepage(Model model){
        model.addAttribute("upcomingTennisMatches", tennisMatchService.getUpcomingTennisMatches());
        return "homepage";
    }
}
