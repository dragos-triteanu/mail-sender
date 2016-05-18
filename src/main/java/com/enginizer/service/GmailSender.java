package com.enginizer.service;

import com.enginizer.exception.MailException;
import com.enginizer.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * GMAIL mail sender.
 */
@Service("gmailMailSender")
public class GmailSender extends MailSender {

    @Autowired
    private org.springframework.mail.MailSender gmailSender;


    @Override
    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("UC8 Sender <uc8sender@gmail.com>");
        message.setTo(defaultRecipient);
        message.setSubject(defaultSubject);
        message.setText(String.format("Message from '%s <%s>':\n %s", email.getName(), email.getEmail(), email.getText()));
        try{
            gmailSender.send(message);
        }catch (Exception e){
            throw new MailException("Could not send gmail mail message",e,500);
        }
    }
}
