package com.springboot.service;

import com.springboot.dto.Message;
import com.springboot.dto.UserRoleDto;
import com.springboot.dto.User_UserRole;

import java.util.List;

public interface UserRoleService {
    UserRoleDto findUserRoleById(String id);

    Message findAll();

    Message save(UserRoleDto userRoleDto);

    Message findById(String id);

    List<User_UserRole> findUserRole(String userId);
}
