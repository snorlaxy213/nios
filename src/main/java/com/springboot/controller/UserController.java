package com.springboot.controller;

import com.springboot.commons.PageUtils;
import com.springboot.dto.Message;
import com.springboot.dto.UserDto;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;


    @Autowired
    @Qualifier("mapper")
    protected Mapper mapper;

    @GetMapping("/toUser")
    public String toUser() {
        return "user/UserProfile";
    }

    @ResponseBody
    @GetMapping("/user")
    public Message getUser(@RequestParam(value = "pageNumber", defaultValue = "0")Integer pageNumber) {
        Message users = userService.findAllWithPage(pageNumber, PageUtils.PAGE_SIZE);

        return users;
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public Message getUserById(@PathVariable(value = "id")String id) {
        Message message = userService.findById(id);

        return message;
    }

    @ResponseBody
    @PostMapping(value = "/user")
    public Message save(@RequestBody UserDto userDto){
        String msg = "";
        try {
            msg = userService.save(userDto);
            return Message.success();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return Message.success();
    }
}
