package org.naamtamilar.magazine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/")
    public String showHomePage(Model model) {
        try {
            logger.info("Showing home page");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                /* The user is logged in :) */
                return "memberHome";
            }
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            model.addAttribute("userNotFound", true);
            return "index";
        }
        model.addAttribute("title","");
        return "index";
    }
}
