package com.springboot.controller;

import com.springboot.commons.PageUtils;
import com.springboot.dto.Message;
import com.springboot.dto.UserDto;
import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /*@GetMapping("/user")
    public List<UserDto> getUser(Model model) {
        List<UserDto> userDtos = userService.findAll();

        model.addAttribute("userDtos", userDtos);
        return userDtos;
    }*/

    @ResponseBody
    @GetMapping("/user")
    public Message getUser(@RequestParam(value = "pageNumber", defaultValue = "0")Integer pageNumber, Model model) {
        Page<User> users = userService.findAllWithPage(pageNumber, PageUtils.PAGE_SIZE);

        return Message.success().add("userInfo",users);
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
