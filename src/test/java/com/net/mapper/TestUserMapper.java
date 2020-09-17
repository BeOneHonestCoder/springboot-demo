package com.net.mapper;

import com.net.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    private Logger logger = LoggerFactory.getLogger(TestUserMapper.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSave() throws Exception {
        User user = new User();
        //user.setId((long)1);
        user.setName("liwei");
        user.setBirthday(new Date());
        user.setCreatets(new Timestamp(new Date().getTime()));

        int result = userMapper.saveUser(user);
        logger.info("" + result);
    }
}
