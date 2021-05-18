package org.naamtamilar.magazine.controller;

import org.naamtamilar.magazine.domain.Mailer;
import org.naamtamilar.magazine.domain.User;
import org.naamtamilar.magazine.service.impl.MailService;
import org.naamtamilar.magazine.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment environment;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserServiceImpl userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class.getName());

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        LOGGER.info("உறுப்பினர் பதிவு பக்கம்  ");
        model.addAttribute("user", new User()).addAttribute("title", "பதிவு - ");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        LOGGER.info("Registering user");
        User userExists = userService.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "Email already exist");
        }
        if (bindingResult.hasErrors()){
            LOGGER.info(bindingResult+"");
            return "register";
        }
        userService.populateDefaultValues(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.info(user.toString());
        userService.saveOrUpdate(user);
        sendMail(user);
        model.addAttribute("registerSuccess",true);
        return "redirect:/login";
    }

    private void sendMail(User user) {
        Mailer mailer=new Mailer();
        mailer.setRecipients(new String[]{user.getEmail()});
        mailer.setCcList(new String[]{environment.getProperty("spring.mail.username")});
        mailer.setSubject("");
        Map<String,String> mailTemplateData=new HashMap<>();
        mailTemplateData.put("userName", user.getName());
        mailTemplateData.put("templateName","mailTemplates/welcomeMail");
        mailService.prepareAndSend(mailer,mailTemplateData);
    }
}
