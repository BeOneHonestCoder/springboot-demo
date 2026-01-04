package com.net.mapper;

import com.net.domain.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testQuery() throws Exception {
        User user = new User();
        user.setName("liwei");

        List<User> users = userMapper.getUsersWithPageSize(user, 10);
        users.forEach(
                record -> logger.info("" + record)
        );
    }
}
