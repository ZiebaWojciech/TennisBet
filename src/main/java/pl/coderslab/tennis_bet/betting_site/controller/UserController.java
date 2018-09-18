package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tennis_bet.betting_site.entity.transitionModel.CurrentUser;
import pl.coderslab.tennis_bet.betting_site.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void setModelAttributes(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public String  userAccount() {
        return "user/account";
    }
    @RequestMapping(path = "/wallet", method = RequestMethod.GET)
    public String  userWallet() {
        return "user/wallet";
    }
    @RequestMapping(path = "/details", method = RequestMethod.GET)
    public String  userDetails() {
        return "user/details";
    }

}

