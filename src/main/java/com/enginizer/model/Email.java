package com.enginizer.model;

/**
 * Created by dragos.triteanu on 5/16/16.
 */
public class Email {

    private String text;
    private String email;
    private String name;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
