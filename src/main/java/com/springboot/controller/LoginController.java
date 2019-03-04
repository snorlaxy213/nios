package com.springboot.controller;

import com.springboot.commons.JWTUtil;
import com.springboot.dto.UserDto;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @PostMapping(value = "user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        //obtain subject
        Subject currentUser = SecurityUtils.getSubject();


        if (!currentUser.isAuthenticated()) {
            //packaging user data
//            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
            UserDto userDto  = userService.findById(username);

            ByteSource credentialsSalt = ByteSource.Util.bytes(userDto.getId());//Use account ID as salt value

            Object result = new SimpleHash("MD5", password, credentialsSalt, 1024);
            //test token
            String token = JWTUtil.sign(username, password);

            if (userDto.getPassword().equals(result.toString())) {
                return "index";
            } else {
                return "login";
            }

            //execute login method
//            try {
//                currentUser.login(usernamePasswordToken);
//                model.addAttribute("token", JWTUtil.sign(username,password));
//                return "index";
//            } catch (UnknownAccountException e) {
//                //user is no t exist
//                model.addAttribute("msg", "user is not exist");
//                return "login";
//            } catch (IncorrectCredentialsException e) {
//                //password is error
//                model.addAttribute("msg", "password is not right");
//                return "login";
//            }
        }

        return "redirect:/index.html";
    }

    @GetMapping(value = "/toLogin")
    public String toLogin(){
        return "login";
    }
}
