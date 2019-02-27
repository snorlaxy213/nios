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

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
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
        Map<String, Object> users = userService.findAllWithPage(pageNumber, PageUtils.PAGE_SIZE);
        return Message.success("success",users);
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public Message getUserById(@PathVariable(value = "id")String id) {
        UserDto userDto = userService.findById(id);
        return Message.success("success").add("user",userDto);
    }

    @ResponseBody
    @PostMapping(value = "/user")
    public Message save(@RequestBody @Valid UserDto userDto) {
        try {
            String message = userService.save(userDto);
            return Message.success(message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @DeleteMapping("/user")
    public Message delete(@RequestBody Map<String,String> userIds) {
        try {
            userService.delete(userIds);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

}
