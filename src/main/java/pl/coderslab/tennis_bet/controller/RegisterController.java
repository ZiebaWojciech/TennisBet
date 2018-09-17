package pl.coderslab.tennis_bet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
    private  final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    public String  registerUserInit(Model model) {
        model.addAttribute("user", new User());
        return "/registration/registration-form";
    }

    @PostMapping(path = "")
    public String registerUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration/registration-form";
        }
        userService.saveUserWithPassword(user);
        return "redirect:/homepage";
    }
}

