package com.springboot.service;

import com.springboot.dto.Message;
import com.springboot.dto.UserRoleDto;

public interface UserRoleService {
    UserRoleDto findUserRoleById(String id);

    Message findAll();

    Message save(UserRoleDto userRoleDto);

    Message findById(String id);
}
