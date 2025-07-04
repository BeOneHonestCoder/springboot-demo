package com.net.mapper;

import com.net.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int saveUser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    int deleteById(@Param("id") Long id);

    List<User> findAll();

    User findById(@Param("id") Long id);

    List<User> getUsersWithPageSize(@Param("user") User user, @Param("pageSize") int pageSize);
}
