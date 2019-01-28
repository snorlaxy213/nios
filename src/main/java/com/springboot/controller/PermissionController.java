package com.springboot.controller;

import com.springboot.dto.Message;
import com.springboot.service.PermissionService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Permission")
public class PermissionController {
    private static final Logger LOGGER = Logger.getLogger(PermissionController.class);

    @Autowired
    @Qualifier("mapper")
    protected Mapper mapper;

    @Autowired
    @Qualifier("permissionServiceImpl")
    PermissionService permissionService;

    @GetMapping
    @RequestMapping("/Permission")
    public Message findPermission(){
        Message message = permissionService.findAll();
        return message;
    }
}
