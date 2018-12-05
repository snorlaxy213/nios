package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Jules Chen
 * @create 2018/11/22
 */
@Controller
public class hello {

//    @RequestMapping({"/","/login.html"})
//    public String login(){
//        return "login";
//    }


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>hello</h1>");
        map.put("list", Arrays.asList("1", "2", "3"));
        return "success";
    }
}
