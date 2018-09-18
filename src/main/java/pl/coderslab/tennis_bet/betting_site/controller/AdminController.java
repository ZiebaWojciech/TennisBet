package pl.coderslab.tennis_bet.betting_site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String homepage(){
        return "homepage";
    }
}
