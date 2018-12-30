package com.springboot.service;

import com.springboot.dto.UserDto;
import com.springboot.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    List<UserDto> findAll();

    Page<User> findAllWithPage(Integer pageNumber, Integer pageSize);

    String save(UserDto userDto);

    User findById(String id);
}
