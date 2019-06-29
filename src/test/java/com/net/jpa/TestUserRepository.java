package com.net.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

    private Logger logger = LoggerFactory.getLogger(TestUserRepository.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){
        Optional<User> optionalUser = userRepository.findById((long) 1);
        User user = optionalUser.get();
        logger.info(user.toString());
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setId((long)1);
        user.setName("zhangsan");
        user.setBirthday(new Date());
        user.setCreatets(new Timestamp(new Date().getTime()));

        User resultUser= userRepository.save(user);
        System.out.println(resultUser);
    }
}
