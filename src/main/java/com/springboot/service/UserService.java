package com.springboot.service;

import com.springboot.dto.UserDto;
import com.springboot.entity.User;

import java.util.List;


public interface UserService {

    List<UserDto> findAll();

    User findByName(String username);

    String save(UserDto userDto);

    User findById(String id);
}
