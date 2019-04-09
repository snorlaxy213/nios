package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dto.UserDto;

import java.util.List;


public interface UserService {

    List<UserDto> findAll();

    PageInfo findAllWithPage(Integer pageNumber, Integer pageSize);

    void save(UserDto userDto, String userId);

    UserDto findById(String id);

    UserDto findByIdWithMapper(String id);

    void delete(List<String> userIdList);

    List<UserDto> findByDoctor();

    int getCurrentNum(String id);

    void descCurrentNum(String id);
}
