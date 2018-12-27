package com.springboot.controller;

import com.springboot.dto.Message;
import com.springboot.dto.UserDto;
import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier("mapper")
    protected Mapper mapper;

    @GetMapping("/toUser")
    public String toUser() {

        return "user/UserProfile";
    }

    @ResponseBody
    @GetMapping("/user")
    public List<UserDto> getUser() {
        try {
            List<User> users = userRepository.findAll();

            List<UserDto> userDtos = new ArrayList<>();
            for (User user : users) {
                UserDto userDto = mapper.map(user,UserDto.class);
                userDtos.add(userDto);
            }
            return userDtos;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    @ResponseBody
    @PostMapping(value = "/user")
    public Message save(UserDto userDto){
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
