package pl.coderslab.tennis_bet.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(path = "/details")
    public String  userAccount(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getUser());
        return "user-account/details";
    }

    @PostMapping(path = "")
    public String registerUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration/registration-form";
        }
        userService.saveNewUser(user);
        return "redirect:/homepage";
    }
}

