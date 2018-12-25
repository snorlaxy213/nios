package com.springboot.service;

import com.springboot.dto.UserDto;
import com.springboot.entity.User;


public interface UserService {
    User findByName(String username) throws Exception;

    String save(UserDto userDto) throws Exception;

    User findById(String id);
}
