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

import java.util.Map;

@Controller
@RequestMapping("/User")
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
    @GetMapping("/User")
    public Message getUser(@RequestParam(value = "pageNumber", defaultValue = "0")Integer pageNumber) {
        Map<String, Object> users = userService.findAllWithPage(pageNumber, PageUtils.PAGE_SIZE);
        return Message.success().setData(users);
    }

    @ResponseBody
    @GetMapping("/User/{id}")
    public Message getUserById(@PathVariable(value = "id")String id) {
        UserDto userDto = userService.findById(id);
        return Message.success().add("user",userDto);
    }

    @ResponseBody
    @PostMapping(value = "/User")
    public Message save(@RequestBody UserDto userDto){
        String message = userService.save(userDto);
        return Message.success(message);
    }
}
