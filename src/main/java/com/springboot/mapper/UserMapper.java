package com.springboot.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userMapper")
public interface UserMapper {
    List<String> usertest();

    @Select(value = "SELECT user_id FROM user_profile")
    List<String> usertest1();

}
