package com.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Jules Chen
 * @create 2018/11/23
 */
@Controller
public class LoginController {

    /*@PostMapping(value = "user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username)&&"123".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            map.put("msg", "password error");
            return "login";
        }
    }*/

    @PostMapping(value = "user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        //obtain subject
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            //packaging user data
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

            //execute login method
            try {
                currentUser.login(usernamePasswordToken);
                return "redirect:/main.html";
            } catch (UnknownAccountException e) {
                //login fail:user is no t exist
                model.addAttribute("msg", "user is not exist");
                return "login";
            } catch (IncorrectCredentialsException e) {
                //password is error
                model.addAttribute("msg", "password is not right");
                return "login";
            }
        }

        return "redirect:/main.html";
    }

    @GetMapping(value = "/toLogin")
    public String toLogin(){
        return "login";
    }
}
