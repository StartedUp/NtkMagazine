package org.naamtamilar.magazine.controller;

import org.naamtamilar.magazine.domain.Mailer;
import org.naamtamilar.magazine.service.impl.MailService;
import org.naamtamilar.magazine.service.impl.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OtpController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Environment environment;
    @Autowired
    public OtpService otpService;
    @Autowired
    public MailService myEmailService;
    @GetMapping("/generateOtp")
    public String generateOtp() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        username = "நாம் தமிழர்";
        int otp = otpService.generateOTP(username);
        logger.info("OTP : " + otp);
        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("username", username);
        replacements.put("otpnum", String.valueOf(otp));
        replacements.put("templateName","mailTemplates/mailTemplateConfirmEmail");
        Mailer mailer = new Mailer();
        mailer.setRecipients(new String[]{environment.getProperty("spring.mail.username")});
        myEmailService.prepareAndSend(mailer, replacements);
        return "index";
    }

    @RequestMapping(value = "/validateOtp", method = RequestMethod.GET)
    public @ResponseBody
    String validateOtp(@RequestParam("otpnum") int otpnum) {
        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.info(" Otp Number : " + otpnum);
        //Validate the Otp
        if (otpnum >= 0) {
            int serverOtp = otpService.getOtp(username);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(username);
                    return ("Entered Otp is valid");
                } else {
                    return SUCCESS;
                }
            } else {
                return FAIL;
            }
        } else {
            return FAIL;
        }
    }
}