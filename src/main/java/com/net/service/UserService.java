package com.net.service;

import com.net.domain.User;
import com.net.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Cacheable(value = "users:detail", key = "#id", sync = true)
    public User findById(Long id) {
        log.warn(">>>> [Cache MISS] User ID: {} not found. Fetching from Database...", id);
        return userMapper.findById(id);
    }

    @CacheEvict(value = "users:detail", key = "#user.id")
    public User saveUser(User user) {
        log.info(">>>> [DB Write] Saving User ID: {}. Cache evicted.", user.getId());
        userMapper.saveUser(user);
        return user;
    }

    @CacheEvict(value = "users:detail", key = "#id")
    public void deleteById(Long id) {
        log.info(">>>> [DB Delete] Deleting User ID: {}. Cache evicted.", id);
        userMapper.deleteById(id);
    }

    @CacheEvict(value = "users:detail", key = "#user.id")
    public User updateUser(User user) {
        log.info(">>>> [DB Update] Updating User ID: {}. Cache evicted.", user.getId());
        userMapper.updateUser(user);
        return user;
    }
}
