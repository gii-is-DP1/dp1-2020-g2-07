package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.samples.petclinic.model.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender gmailSender;

    public void sendMail(Email e)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(e.getAddress());
        message.setSubject(e.getSubject());
        message.setText(e.getBody());
        gmailSender.send(message);
    }

}
