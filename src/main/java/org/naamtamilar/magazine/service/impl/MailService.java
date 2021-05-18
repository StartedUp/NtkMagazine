package org.naamtamilar.magazine.service.impl;

import org.naamtamilar.magazine.domain.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class MailService {
    private static Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;
    @Autowired
    private Environment environment;
    @Autowired
    private UserServiceImpl userService;
    @Value("${mail.event.notification}")
    private String eventNotification;

    @Autowired
    public MailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void prepareAndSend(Mailer mailer, Map<String, String> mailTemplateData) {
        LOGGER.info("Sending mail");
        LOGGER.info("Mail creds from {} password {}", environment.getProperty("spring.mail.username"),
                environment.getProperty("spring.mail.password")
        );
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            messageHelper.setTo(mailer.getRecipients());
            if (mailer.getBccList() != null)
                messageHelper.setBcc(mailer.getBccList());
            if (mailer.getCcList() != null)
                messageHelper.setCc(mailer.getCcList());
            if (mailer.getSubject() != null)
                messageHelper.setSubject(mailer.getSubject());
            LOGGER.info("Building Template for mail with subject : {}",mailer.getSubject());
            String content = build(mailTemplateData);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
            LOGGER.info("Error while sending mail {}", e);
        }
    }

    private String build(Map<String, String> mailTemplateData) {
        Context context = new Context();
        for(Map.Entry<String, String> entry : mailTemplateData.entrySet()) {
            String templateVariableName = entry.getKey();
            String templateVariableValue = entry.getValue();
            context.setVariable(templateVariableName, templateVariableValue);
        }
        return templateEngine.process(mailTemplateData.get("templateName"), context);
    }
}
