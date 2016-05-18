package com.enginizer.service;

import com.enginizer.exception.MailException;
import com.enginizer.model.Email;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by dragos.triteanu on 5/18/16.
 */
public abstract class MailSender {

    @Value("${mail.default.recipients}")
    protected String defaultRecipient;

    @Value("${mail.default.subject}")
    protected String defaultSubject;

    public abstract void sendEmail(final Email email) throws MailException;

}
