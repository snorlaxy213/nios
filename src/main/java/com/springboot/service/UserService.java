package com.springboot.service;

import com.springboot.dto.Message;
import com.springboot.dto.UserDto;

import java.util.List;


public interface UserService {

    List<UserDto> findAll();

    Message findAllWithPage(Integer pageNumber, Integer pageSize);

    String save(UserDto userDto);

    Message findById(String id);
}
