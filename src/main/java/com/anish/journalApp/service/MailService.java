package com.anish.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;


    public void sendMail(String to, String subject, String Body){

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(Body);
            javaMailSender.send(mail);

        } catch (Exception e) {
            log.error("Exception while emailing",e);
        }
    }
}
