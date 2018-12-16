package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @GetMapping("/user/{id}")
    public List<User> getUser(@PathVariable("id") Long id) {

        List<User> users = userRepository.findAll();
        return users;
    }


    @GetMapping("/user/{type}")
    public String insertUser(User user, @PathVariable("type") String type) {

        if ("page".equals(type)){
            return "user/UserProfile";
        }
        User save = userRepository.save(user);
        return "save";
    }

    @GetMapping("/toUser")
    public String toUser() {

        return "user/UserProfile";
    }


}
