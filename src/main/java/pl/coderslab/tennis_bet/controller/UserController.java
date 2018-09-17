package pl.coderslab.tennis_bet.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.entity.transitionModel.CurrentUser;
import pl.coderslab.tennis_bet.service.UserService;

import javax.validation.Valid;

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

    @GetMapping(path = "/account")
    public String  userAccount() {
        return "user/account";
    }
    @GetMapping(path = "/wallet")
    public String  userWallet() {
        return "user/wallet";
    }
    @GetMapping(path = "/details")
    public String  userDetails() {
        return "user/details";
    }

}

