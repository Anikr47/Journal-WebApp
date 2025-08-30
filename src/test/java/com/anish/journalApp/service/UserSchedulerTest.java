package com.anish.journalApp.service;

import com.anish.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {
    @Autowired
    UserScheduler userScheduler;
    @Disabled
    @Test
    public void testForMailSA(){
        userScheduler.fetchUserAndSendMail();

    }
}
