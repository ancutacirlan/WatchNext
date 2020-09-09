package com.example.WatchNext.security.jwt;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {


    private JavaMailSender javaMailSender;

    public SendEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String setTo, String setSubject, String setText) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(setTo);
        msg.setSubject(setSubject);
        msg.setText(setText);
        javaMailSender.send(msg);

    }
}