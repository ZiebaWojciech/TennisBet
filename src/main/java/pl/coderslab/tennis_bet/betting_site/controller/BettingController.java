package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketStatus;
import pl.coderslab.tennis_bet.betting_site.entity.transient_model.CurrentUser;
import pl.coderslab.tennis_bet.betting_site.service.BetSelectionService;
import pl.coderslab.tennis_bet.betting_site.service.BetTicketService;
import pl.coderslab.tennis_bet.betting_site.entity.TennisMatch;
import pl.coderslab.tennis_bet.betting_site.service.TennisMatchService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping(path = "/betting")
public class BettingController {
    @Autowired
    TennisMatchService tennisMatchService;
    @Autowired
    BetTicketService betTicketService;
    @Autowired
    BetSelectionService betSelectionService;

    @ModelAttribute
    public void setEmptyTicketAndUpcomingMatches(@AuthenticationPrincipal CurrentUser currentUser, HttpServletRequest request, Model model) {
        BetTicket ticket = (BetTicket) request.getSession().getAttribute("ticket");
        if (currentUser != null) {
            model.addAttribute("user", currentUser.getUser());
            if (ticket == null) {
                BetTicket betTicket = new BetTicket();
                betTicket.setUser(currentUser.getUser());
                betTicket.setBetTicketStatus(BetTicketStatus.CREATED_NOT_SUBMITTED);
                request.getSession().setAttribute("ticket", betTicket);
            }
        }
        model.addAttribute("upcomingTennisMatches", tennisMatchService.getUpcomingTennisMatches());
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String sportBettingAllMarkets() {
        return "betting/all-markets";
    }

    @RequestMapping(path = "/add-to-ticket/{gameId}", method = RequestMethod.POST)
    public String addBetToTicket(@PathVariable int gameId, @RequestParam("betSelectionType") String betSelectionType, @RequestParam("odd") BigDecimal odd, HttpServletRequest request, Model model) {
        BetTicket betTicket = ((BetTicket) request.getSession().getAttribute("ticket"));
        TennisMatch tennisMatch = tennisMatchService.getOne(gameId);
        if (!betSelectionService.isBetUniqueForEvent(betTicket, tennisMatch)) {
            model.addAttribute("doubleBet", "You cannot bet twice on this same event");
        } else {
            betTicket = betSelectionService.addNewBetSelectionToBetTicket(betTicket, betSelectionType, odd, tennisMatch);
        }
        request.getSession().setAttribute("ticket", betTicket);
        return "betting/all-markets";
    }


    @RequestMapping(path = "/submit-ticket", method = RequestMethod.POST)
    public String submitTicket(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("stake") BigDecimal stake, HttpServletRequest request, Model model) {
        BetTicket betTicket = ((BetTicket) request.getSession().getAttribute("ticket"));
        if (BigDecimal.ZERO.compareTo(stake) >= 0) {
            model.addAttribute("stakeMessage", "You cannot bet nothing or less!");
            return "/betting/all-markets";
        } else if (currentUser.getUser().getWallet().getBalance().compareTo(stake) <= 0) {
            model.addAttribute("notEnoughFunds", true);
            return "/betting/all-markets";
        }
        if (betTicketService.hasOddsChanged(betTicket)) {
            model.addAttribute("oddsChanged", true);
            return "/betting/all-markets";
        }
        betTicketService.submitTicket(stake, betTicket);
        request.getSession().removeAttribute("ticket");
        return "redirect:/betting/all";
    }

    @RequestMapping(path = "/remove-bet", method = RequestMethod.POST)
    public String removeBetSelectionFromTicket(@RequestParam("temporalId") UUID temporalId, HttpServletRequest request, Model model) {
        BetTicket betTicket = ((BetTicket) request.getSession().getAttribute("ticket"));
        betTicketService.removeBetSelectionFromTicket(betTicket, temporalId);
        return "redirect:/betting/all";
    }

    @RequestMapping(path = "/delete-ticket", method = RequestMethod.GET)
    public String deleteTicket(HttpServletRequest request) {
        request.getSession().removeAttribute("ticket");
        return "redirect:/betting/all";
    }


}
