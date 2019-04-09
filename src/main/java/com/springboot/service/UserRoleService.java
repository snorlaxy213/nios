package com.springboot.service;

import com.springboot.dto.UserRoleDto;
import com.springboot.dto.User_UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRoleDto> findAll();

    String save(UserRoleDto userRoleDto,String userId);

    UserRoleDto findById(String id);

    List<User_UserRole> findUser_Role(String userId);

    void delete(List<String> userRoleIdList);
}
