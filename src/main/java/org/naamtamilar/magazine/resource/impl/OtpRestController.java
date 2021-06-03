package org.naamtamilar.magazine.resource.impl;

import org.naamtamilar.magazine.domain.Mailer;
import org.naamtamilar.magazine.resource.GlobalRestMapping;
import org.naamtamilar.magazine.service.impl.MailService;
import org.naamtamilar.magazine.service.impl.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OtpRestController implements GlobalRestMapping {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Environment environment;
    @Autowired
    public OtpService otpService;
    @Autowired
    public MailService myEmailService;
    @PostMapping("/generateOtp")
    public ResponseEntity generateOtp(@RequestParam("email") String email ) {
       /* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();*/
        int otp = otpService.generateOTP(email);
        logger.info("OTP : " + otp);
        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("username", email);
        replacements.put("otpnum", String.valueOf(otp));
        replacements.put("templateName","mailTemplates/mailTemplateConfirmEmail");
        Mailer mailer = new Mailer();
        mailer.setRecipients(new String[]{email});
        //myEmailService.prepareAndSend(mailer, replacements);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
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
        validateOtp(otpnum, SUCCESS, FAIL, username);
        return "true";
    }

    public boolean validateOtp(int otpnum, String SUCCESS, String FAIL, String username) {
        if (otpnum >= 0) {
            int serverOtp = otpService.getOtp(username);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(username);
                    return true;
                }
            }
        }
        return true;
    }
}