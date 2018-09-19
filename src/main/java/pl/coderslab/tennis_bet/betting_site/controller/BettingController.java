package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tennis_bet.betting_site.entity.BetSelection;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionStatus;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetSelectionType;
import pl.coderslab.tennis_bet.betting_site.entity.transitionModel.CurrentUser;
import pl.coderslab.tennis_bet.sportDataFeed.service.TennisMatchService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping(path = "/betting")
public class BettingController {
    @Autowired
    TennisMatchService tennisMatchService;

    @ModelAttribute
    public void setSessionAttributes(@AuthenticationPrincipal CurrentUser currentUser, HttpServletRequest request, Model model) {
        BetTicket ticket = (BetTicket) request.getSession().getAttribute("ticket");
        if(currentUser != null){
            if (ticket == null) {
                BetTicket betTicket = new BetTicket();
                betTicket.setUser(currentUser.getUser());
                request.getSession().setAttribute("ticket", betTicket);
            }
        }
        model.addAttribute("upcomingTennisMatches", tennisMatchService.getUpcomingTennisMatches());
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String betting() {
        return "betting/all-markets";
    }

    @RequestMapping(path = "/add-to-ticket/{gameId}", method = RequestMethod.POST)
    public String addBetToTicket( @PathVariable int gameId, @RequestParam("betSelectionType") String betSelectionType, @RequestParam("odd") BigDecimal odd, HttpServletRequest request) {
        BetTicket betTicket = ((BetTicket) request.getSession().getAttribute("ticket"));
        BetSelection betSelection = new BetSelection();
        betSelection.setBetSelectionStatus(BetSelectionStatus.PENDING);
        betSelection.setTennisMatch(tennisMatchService.getOne(gameId));
        betSelection.setOdd(odd);
        if (betSelectionType.equals("PlayerOne")) {
            betSelection.setBetSelectionType(BetSelectionType.PLAYER_ONE_WINS);
        } else {
            betSelection.setBetSelectionType(BetSelectionType.PLAYER_TWO_WINS);
        }
        betTicket.addBetSelection(betSelection);
        request.getSession().setAttribute("ticket", betTicket);
        return "redirect:/betting/all";
    }

}
