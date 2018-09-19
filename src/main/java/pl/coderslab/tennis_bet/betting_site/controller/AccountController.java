package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennis_bet.betting_site.entity.BetTicket;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketResult;
import pl.coderslab.tennis_bet.betting_site.entity.enumUtil.BetTicketStatus;
import pl.coderslab.tennis_bet.betting_site.entity.transitionModel.CurrentUser;
import pl.coderslab.tennis_bet.betting_site.service.BetTicketService;

@Controller
@RequestMapping(path = "/user")
public class AccountController {
    @Autowired
    BetTicketService betTicketService;

    @ModelAttribute
    public void setModelAttributes(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
        model.addAttribute("tickets", betTicketService.getAllByUser(currentUser.getUser()));

    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public String  userAccount() {
        return "user/account";
    }

    @RequestMapping(path = "/bets", method = RequestMethod.GET)
    public String  userBets() {
        return "user/tickets";
    }
    @RequestMapping(path = "/bets/details/{ticketId}", method = RequestMethod.GET)
    public String  userBets(@PathVariable int ticketId, @AuthenticationPrincipal CurrentUser currentUser, Model model) {

        model.addAttribute("ticket", betTicketService.getOne(ticketId));
        return "user/ticket-details";
    }

    @RequestMapping(path = "/bets/cash-out/{ticketId}", method = RequestMethod.GET)
    public String  singleTicketCashOut(@PathVariable int ticketId, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        BetTicket betTicket = betTicketService.getOne(ticketId);
        if(!betTicket.getBetTicketResult().equals(BetTicketResult.WON)){
            model.addAttribute("cannotCashOut", "You are not allowed to cash out this ticket");
            return "user/ticket-details";
        }
        betTicketService.cashOutTicket(betTicket, currentUser.getUser());
        return "redirect:/user/bets";
    }
}
