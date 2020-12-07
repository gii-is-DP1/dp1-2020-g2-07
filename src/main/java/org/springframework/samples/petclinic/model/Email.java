package org.springframework.samples.petclinic.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

public class Email {
    @NotEmpty
    private String address;

    @NotEmpty
    private String subject;

    @NotEmpty
    private String body;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
