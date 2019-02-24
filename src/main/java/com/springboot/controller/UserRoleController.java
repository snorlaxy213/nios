package com.springboot.controller;

import com.springboot.dto.Message;
import com.springboot.dto.UserRoleDto;
import com.springboot.service.UserRoleService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/UserRole")
public class UserRoleController {

    private static final Logger LOGGER = Logger.getLogger(UserRoleController.class);

    @Autowired
    Mapper mapper;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @ResponseBody
    @GetMapping("UserRole")
    public Message findAll() {
        Message message = userRoleService.findAll();

        return message;
    }

    @ResponseBody
    @PostMapping("/UserRole")
    public Message save(@RequestBody UserRoleDto userRoleDto) {
        userRoleService.save(userRoleDto);

        return Message.success("success");
    }

    @ResponseBody
    @GetMapping("/UserRole/{id}")
    public Message findById(@PathVariable(value = "id") String id) {
        Message message = userRoleService.findById(id);

        return message;
    }

}
