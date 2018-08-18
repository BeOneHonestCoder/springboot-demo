package com.net.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){
        Optional<User> optionalUser = userRepository.findById((long) 1);
        User user = optionalUser.get();
    }
}
