package com.springboot.service.serviceImpl;

import com.springboot.dto.Message;
import com.springboot.entity.Permission;
import com.springboot.repository.PermissionRepository;
import com.springboot.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Message findAll() {
        List<Permission> permissions = permissionRepository.findAll();

        if (permissions != null) {
            return Message.success().add("permissions", permissions);
        } else {
            return Message.fail();
        }

    }
}
