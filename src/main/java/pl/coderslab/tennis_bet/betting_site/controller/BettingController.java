package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisMatchService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/betting")
public class BettingController {
    @Autowired
    TennisMatchService tennisMatchService;

    @ModelAttribute
    public void setSessionAttributes(HttpServletRequest request){
        BetTicket ticket = (BetTicket) request.getSession().getAttribute("ticket");
        if(ticket == null){
            request.getSession().setAttribute("ticket", new BetTicket());
        }
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String betting(Model model){
        model.addAttribute("upcomingTennisMatches", tennisMatchService.getUpcomingTennisMatches());
        return "betting/all-markets";
    }
}
