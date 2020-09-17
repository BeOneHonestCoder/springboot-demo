package com.net.mapper;

import com.net.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int saveUser(@Param("user") User user);

    int deleteById(@Param("id") Long id);
}
