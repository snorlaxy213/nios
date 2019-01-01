package com.springboot.service.serviceImpl;

import com.springboot.dto.UserRoleDto;
import com.springboot.entity.UserRole;
import com.springboot.repository.UserRoleRepository;
import com.springboot.service.UserRoleService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userRoleServiceImpl")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger LOGGER = Logger.getLogger(UserRoleServiceImpl.class);

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRoleDto findUserRoleById(String id) {
        return null;
    }

    @Override
    public List<UserRoleDto> findUserRole() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        userRoles.forEach(userRole -> {
            UserRoleDto userRoleDto = mapper.map(userRole,UserRoleDto.class);
            userRoleDtos.add(userRoleDto);
        });
        return userRoleDtos;
    }
}
