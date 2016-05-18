package com.enginizer.service;

import com.enginizer.exception.MailException;
import com.enginizer.model.Email;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

/**
 * Mailgun mail sender.
 */
@Service("mailgunMailSender")
public class MailgunSender extends MailSender {
    private static final Logger LOG = LoggerFactory.getLogger(MailgunSender.class);

    @Autowired
    private WebResource mailgunClient;

    @Override
    public void sendEmail(Email email) {
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "UC8 Poster <postmaster@uc8.co>");
        formData.add("to", defaultRecipient);
        formData.add("subject", defaultSubject);
        formData.add("text", String.format("Message from '%s <%s>':\n %s",email.getName(),email.getEmail(),email.getText()));

        ClientResponse clientResponse = mailgunClient.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        String responseAsString = clientResponse.getEntity(String.class);

        if(200 != clientResponse.getStatus()){
            throw new MailException(responseAsString,clientResponse.getStatus());
        }

        LOG.info("Mail sending status:" + clientResponse.getStatus() + " with response: "+ responseAsString);

    }
}
