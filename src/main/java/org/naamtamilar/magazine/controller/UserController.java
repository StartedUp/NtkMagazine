package org.naamtamilar.magazine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/u")
public class UserController {
    @GetMapping("/home")
    public String showLoginPage(){
        return "index";
    }
}
