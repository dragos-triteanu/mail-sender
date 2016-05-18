package com.enginizer.exception;

/**
 * Created by dragos.triteanu on 5/18/16.
 */
public class MailException extends RuntimeException {

    private int status;

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }


    public MailException(String message){
        super(message);
    }

    public MailException(String message, int status){
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
