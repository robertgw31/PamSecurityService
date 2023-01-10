package com.senutech.pam.security.app.service;

import com.senutech.pam.security.app.exception.PamException;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private Configuration configuration;

    @Value( "${spring.mail.from}" )
    private String fromEmail;


    public void sendSimpleMessage(String to, String subject, String text) throws PamException {

        try {
            //SimpleMailMessage message = new SimpleMailMessage();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            sendMailAsync(message);
        } catch(Exception e) {
            throw PamException.normalize("Error sending",e);
        }
    }

    public void sendEmailConfirmationMessage(String userFullName, String userEmail, String validationLink) throws PamException {
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("userFullName",userFullName);
        parameters.put("userEmail",userEmail);
        parameters.put("validationLink",validationLink);
        String htmlContent = getTemplate("email.ftlh",parameters);
        sendHtmlMessage(userEmail,"PAM Registration Email Confirmation",htmlContent);
    }

    public void sendHtmlMessage(String to, String subject, String htmlBody) throws PamException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setFrom(fromEmail);
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            sendMailAsync(message);
        } catch(Exception e) {
            throw PamException.normalize("Failure to send HTML messages",e);
        }
    }

    private void sendMailAsync(MimeMessage message)  {
        taskExecutor.execute( new Runnable() {
            public void run() {
                try {
                    javaMailSender.send(message);
                } catch (Exception e) {
                    PamException ex = PamException.normalize("Fialure to send email", e);

                }
            }

        });
    }

    private String getTemplate(String templateFileName, Map<String,Object> parameters) throws PamException {
        try {
            StringWriter stringWriter = new StringWriter();
            configuration.getTemplate(templateFileName).process(parameters, stringWriter);
            return stringWriter.getBuffer().toString();
        }catch(Exception e) {
            throw PamException.normalize(String.format("Failure to process template '%s'",templateFileName),e);
        }
    }
}

