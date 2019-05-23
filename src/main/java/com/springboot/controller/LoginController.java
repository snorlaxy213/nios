package com.springboot.controller;

import com.springboot.commons.JWTToken;
import com.springboot.commons.JWTUtil;
import com.springboot.dto.UserDto;
import com.springboot.dto.UserRoleDto;
import com.springboot.mapper.UserMapper;
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

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("userMapper")
    UserMapper userMapper;

    @PostMapping(value = "user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Model model, HttpServletRequest request){
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
                    try {
                        UserDto userDto  = userService.findById(username);
                        if (userDto == null) {
                            throw new UnknownAccountException("用户不存在");
                        }
                        UserDto mapperUserByID = userMapper.findUserByID(username);
                        UserRoleDto userRoleDto = mapperUserByID.getUserRoleDtos().get(0);
                        if ("N".equals(userRoleDto.getStatus())) {
                            throw new UnknownAccountException("用户不可用");
                        }

                        ByteSource credentialsSalt = ByteSource.Util.bytes(userDto.getId());//Use account ID as salt value
                        Object result = new SimpleHash("MD5", password, credentialsSalt, 1024);

                        String authorization = JWTUtil.sign(username, result.toString());
                        JWTToken token = new JWTToken(authorization);
                        currentUser.login(token);
                        request.getSession().setAttribute("userId",username);
                        model.addAttribute("token", authorization);
                        return "index";
            } catch (UnknownAccountException e) {
                model.addAttribute("msg", e.getMessage());
                return "login";
            } catch (IncorrectCredentialsException e) {
                model.addAttribute("msg", "密码错误");
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
