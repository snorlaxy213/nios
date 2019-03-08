package com.springboot.mapper;

import com.springboot.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userMapper")
public interface UserMapper {
    List<UserDto> findUser();

    UserDto findUserByID(@Param("userID") String userID);
}
