package com.net.service;

import com.net.domain.User;
import com.net.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MysqlService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public User saveUser(User user) {
        userMapper.saveUser(user);
        return user;
    }

    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }
}
