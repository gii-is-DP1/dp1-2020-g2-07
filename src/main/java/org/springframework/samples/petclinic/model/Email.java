package org.springframework.samples.petclinic.model;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Email {
    @NotEmpty
    private String[] address;

    @NotEmpty
    @Size(min = 3, max = 40)
    private String subject;

    @NotEmpty
    @Size(min = 3, max = 400)
    private String body;

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
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
