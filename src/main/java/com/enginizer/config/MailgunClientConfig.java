package com.enginizer.config;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by dragos.triteanu on 5/16/16.
 */
@Configuration
public class MailgunClientConfig {

    @Value("${mailgun.api.key}")
    private String mailgunApiKey;

    @Value("${mailgun.url}")
    private String mailgunUrl;

    @Bean
    public WebResource mailgunClient(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", mailgunApiKey));

        return client.resource(mailgunUrl);
    }


    @Bean
    public MailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("uc8sender@gmail.com");
        mailSender.setPassword("matt2016");

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth","true");
        props.setProperty("mail.smtp.starttls.enable","true");

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }


}
