package org.naamtamilar.magazine.controller;

import org.naamtamilar.magazine.domain.Mailer;
import org.naamtamilar.magazine.domain.User;
import org.naamtamilar.magazine.service.impl.MailService;
import org.naamtamilar.magazine.service.impl.OtpService;
import org.naamtamilar.magazine.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    public OtpService otpService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class.getName());

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        LOGGER.info("உறுப்பினர் பதிவு பக்கம்");
        model.addAttribute("user", new User()).addAttribute("title", "பதிவு - ");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid User user, @RequestParam("otpPassword") Integer otpPassword,
                           BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes){
        LOGGER.info("Registering user    ");
        User userExists = userService.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "Email already exist");
            return "register";
        }
        if (bindingResult.hasErrors()){
            LOGGER.info(bindingResult+"");
            return "register";
        }
        userService.populateDefaultValues(user);
        user.setAccountVerified(otpService.validateOtp(otpPassword, user.getEmail()));
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        LOGGER.info(user.toString());
        user=userService.saveOrUpdate(user);
        if (user.getId()>0) {
            authWithHttpServletRequest(request,user.getEmail(),password);
            sendMail(user);
            redirectAttributes.addAttribute("isRegistered",true).addAttribute("isAccountVerified", user.isAccountVerified());
        }
        return "redirect:/u/home";
    }

    private void sendMail(User user) {
        Mailer mailer=new Mailer();
        mailer.setRecipients(new String[]{user.getEmail()});
        mailer.setCcList(new String[]{environment.getProperty("spring.mail.username")});
        mailer.setSubject("");
        Map<String,String> mailTemplateData=new HashMap<>();
        mailTemplateData.put("userName", user.getName());
        mailTemplateData.put("templateName","mailTemplates/welcomeMail");
        //mailService.prepareAndSend(mailer,mailTemplateData);
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            LOGGER.error("Error while login ", e);
        }
    }
}
