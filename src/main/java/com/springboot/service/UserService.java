package com.springboot.service;

import com.springboot.dto.UserDto;

import java.util.List;
import java.util.Map;


public interface UserService {

    List<UserDto> findAll();

    Map<String, Object> findAllWithPage(Integer pageNumber, Integer pageSize);

    String save(UserDto userDto);

    UserDto findById(String id);

    void delete(List<String> userIdList);

    List<UserDto> findByDoctor();
}
