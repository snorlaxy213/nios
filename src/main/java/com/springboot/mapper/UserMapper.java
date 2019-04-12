package com.springboot.mapper;

import com.springboot.dto.UserDto;
import com.springboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userMapper")
public interface UserMapper {
    List<UserDto> findAll();

    UserDto findUserByID(@Param("userID") String userID);

    List<UserDto> findWithExample(User user);
}
