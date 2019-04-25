package com.springboot.mapper;

import com.springboot.dto.UserRoleDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userRoleMapper")
public interface UserRoleMapper {
    List<UserRoleDto> findUserRole();

    List<UserRoleDto> findAll();
}
