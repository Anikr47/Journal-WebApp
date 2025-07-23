package com.anish.journalApp.service;

import com.anish.journalApp.entity.User;
import com.anish.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepo userRepo;
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Ram",
            "Anish"
    })
   public void findByUserName(String name){

        User user = userRepo.findByUserName(name);
        assertNotNull(user);

    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "3,3,9",
            "2,4,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
