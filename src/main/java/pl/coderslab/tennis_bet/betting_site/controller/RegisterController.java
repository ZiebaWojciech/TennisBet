package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.tennis_bet.betting_site.entity.User;
import pl.coderslab.tennis_bet.betting_site.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
    private  final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String  registerUserInit(Model model) {
        model.addAttribute("user", new User());
        return "registration/registration-form";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration/registration-form";
        }
        if(userService.getAll().stream().anyMatch(u->u.getUsername().equals(user.getUsername()))){
            model.addAttribute("takenUsername", "Username taken");
            return "registration/registration-form";

        }
        if(userService.getAll().stream().anyMatch(u->u.getEmail().equals(user.getEmail()))){
            model.addAttribute("takenEmail", "Email taken");
            return "registration/registration-form";
        }
        userService.saveNewUser(user);
        return "redirect:/login";
    }
}

