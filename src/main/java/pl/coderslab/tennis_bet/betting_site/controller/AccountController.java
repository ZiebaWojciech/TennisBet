package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.entity.transitionModel.CurrentUser;
import pl.coderslab.tennis_bet.betting_site.service.BetTicketService;

@Controller
@RequestMapping(path = "/user/account")
public class AccountController {
    @Autowired
    BetTicketService betTicketService;

    @ModelAttribute
    public void setModelAttributes(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String  userAccount() {
        return "user/account";
    }

    @RequestMapping(path = "/bets", method = RequestMethod.GET)
    public String  userBets(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("bets", betTicketService.getAllByUser(user));
        return "user/bets";
    }
}
