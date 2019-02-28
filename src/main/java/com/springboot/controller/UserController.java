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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    public Message save(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                List<String> errorMessages = new ArrayList<>();
                for (ObjectError error : allErrors) {
                    String defaultMessage = error.getDefaultMessage();
                    errorMessages.add(defaultMessage);
                }
                return Message.validation(300, errorMessages);
            }
            String message = userService.save(userDto);
            return Message.success(message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @DeleteMapping("/user/{userIds}")
    public Message delete(@PathVariable String userIds) {
        try {
            List<String> userList = Arrays.asList(userIds.split("-"));
            userService.delete(userList);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

}
