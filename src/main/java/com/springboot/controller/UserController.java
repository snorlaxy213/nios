package com.springboot.controller;

import com.springboot.dto.UserDto;
import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
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

    @Autowired
    @Qualifier("userServiceimpl")
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
    public List<UserDto> getUser() throws Exception {
        try {
            List<User> users = userRepository.findAll();

            List<UserDto> userDtos = new ArrayList<>();
            for (User user : users) {
                UserDto userDto = mapper.map(user,UserDto.class);
                userDtos.add(userDto);
            }
            return userDtos;
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/user")
    public void save(UserDto userDto){
        try {
            userService.save(userDto);
        } catch (Exception e) {

        }
    }

}
