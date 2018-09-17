package pl.coderslab.tennis_bet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping(path = "/homepage")
public class HomepageController {
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String homepage(){
        return "homepage";
    }
}
