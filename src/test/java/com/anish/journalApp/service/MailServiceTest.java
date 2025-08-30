package com.anish.journalApp.service;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Disabled
     @Test
     void mailServiceTest(){
        mailService.sendMail("yourEmail@gmail.com","Testing java mail sender", "What's up bro!");
    }
}
