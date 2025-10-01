package com.anish.journalApp.repository;

import com.anish.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
@SpringBootTest
@Disabled
public class UserRepositoryIMPLTest {

    @Autowired
    private UserRepositoryIMPL userRepository;

    @Disabled
    @Test
    public void testForSA(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
