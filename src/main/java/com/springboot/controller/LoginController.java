package com.springboot.controller;

import com.springboot.commons.JWTToken;
import com.springboot.commons.JWTUtil;
import com.springboot.dto.UserDto;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
                        @RequestParam("password") String password, Model model){
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            try {
                UserDto userDto  = userService.findById(username);
                if (userDto == null) {
                    throw new UnknownAccountException("User didn't existed!");
                }
                ByteSource credentialsSalt = ByteSource.Util.bytes(userDto.getId());//Use account ID as salt value
                Object result = new SimpleHash("MD5", password, credentialsSalt, 1024);

                String authorization = JWTUtil.sign(username, result.toString());
                JWTToken token = new JWTToken(authorization);
                currentUser.login(token);
                model.addAttribute("token", authorization);
                return "index";
            } catch (UnknownAccountException e) {
                model.addAttribute("msg", "user is not exist");
                return "login";
            } catch (IncorrectCredentialsException e) {
                model.addAttribute("msg", "password is not right");
                return "login";
            }
        }
        return "index";
    }

    @GetMapping(value = "/toLogin")
    public String toLogin(){
        return "login";
    }
}
