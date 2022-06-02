package com.senutech.pam.security.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Value( "${spring.mail.host}" )
    private String host;
    @Value( "${spring.mail.port}" )
    private int port;
    @Value( "${spring.mail.protocol}" )
    private String protocol;
    @Value( "${spring.mail.username}" )
    private String userName;
    @Value( "${spring.mail.password}" )
    private String password;
    @Value( "${spring.mail.from}" )
    private String fromEmail;
    @Value( "${spring.mail.properties.mail.smtp.auth}" )
    private boolean smtpAuth;
    @Value( "${spring.mail.properties.mail.smtp.starttls.enable}" )
    private boolean smtpStartTtls;

    @Value( "${spring.mail.properties.mail.smtp.socketFactory.port}")
    private int socketFactoryPort;

    @Value( "${spring.mail.debug}" )
    private boolean debug;
    @Value("${mail.template.folder}")
    private String mailTemplateFolder;


    @Bean
    public JavaMailSender emailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();


        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", smtpStartTtls);
        props.put("mail.smtp.socketFactory.port",socketFactoryPort);
        props.put("mail.debug", debug);
        mailSender.setJavaMailProperties(props);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        session.setDebugOut(System.err);


        mailSender.setSession(session);
        return mailSender;
    }


    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(mailTemplateFolder);
        return messageSource;
    }


}
