package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dto.UserDto;

import java.util.List;


public interface UserService {
    //Mybatis
    PageInfo findAllByMybatis(Integer pageNumber, Integer pageSize);

    UserDto findByIdWithMapper(String id);

    List<UserDto> findByDoctorAndOffice(String office, String name);

    //Jpa
    List<UserDto> findAll(Integer pageNumber, Integer pageSize);

    void save(UserDto userDto, String userId);

    void delete(List<String> userIdList);

    List<UserDto> findByDoctor();

    UserDto findById(String id);

    int getCurrentNum(String id);

    void descCurrentNum(String id);
}
