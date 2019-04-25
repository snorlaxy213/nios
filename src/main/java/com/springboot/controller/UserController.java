package com.springboot.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.pagehelper.PageInfo;
import com.springboot.commons.PageUtils;
import com.springboot.commons.PhoneRandomUtils;
import com.springboot.dto.Message;
import com.springboot.dto.PasswordRequestDto;
import com.springboot.dto.UserDto;
import com.springboot.mapper.UserMapper;
import com.springboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    @Qualifier("userMapper")
    UserMapper userMapper;

    @GetMapping("/toUser")
    public String toUser() {
        return "user/UserProfile";
    }

    @ResponseBody
    @GetMapping("/user")
    @RequiresAuthentication
    public Message findAll(@RequestParam(value = "pageNumber", defaultValue = "1")Integer pageNumber) {
        try {
            PageInfo pageInfo = userService.findAllByMybatis(pageNumber, PageUtils.PAGE_SIZE);
            return Message.success().add("pageInfo",pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    @RequiresAuthentication
    public Message getUserById(@PathVariable(value = "id")String id) {
        try {
            UserDto userDto = userService.findByIdWithMapper(id);
            return Message.success("success").add("user",userDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @PostMapping(value = "/user")
    @RequiresAuthentication
    public Message save(@RequestBody @Valid UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        try {
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                List<String> errorMessages = new ArrayList<>();
                for (ObjectError error : allErrors) {
                    String defaultMessage = error.getDefaultMessage();
                    errorMessages.add(defaultMessage);
                }
                if (handleUserRoleNull(userDto) != null) {
                    errorMessages.add(handleUserRoleNull(userDto));
                }
                return Message.validation(300, errorMessages);
            } else if (handleUserRoleNull(userDto) != null){
                List<String> errorMessages = new ArrayList<>();
                errorMessages.add(handleUserRoleNull(userDto));
                return Message.validation(300, errorMessages);
            }

            String userId = (String) request.getSession().getAttribute("userId");
            userService.save(userDto, userId);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @DeleteMapping("/user/{userIds}")
    @RequiresAuthentication
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

    @ResponseBody
    @GetMapping("/userByDoctor")
    @RequiresAuthentication
    public Message findByDoctor() {
        try {
            List<UserDto> byDoctor = userService.findByDoctor();
            return Message.success().add("list",byDoctor);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/userByDoctorAndOffice")
    @RequiresAuthentication
    public Message findByDoctorAndOffice(@RequestParam("office") String office, @RequestParam("doctor") String doctor) {
        try {
            List<UserDto> byDoctor = userService.findByDoctorAndOffice(office, doctor);
            return Message.success().add("list",byDoctor);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @PostMapping("/getVerificationCode")
    public Message getVerificationCode(HttpServletRequest request) {
        String verificationCode = PhoneRandomUtils.randomBuilder();
        request.getSession().setAttribute("msgCode",verificationCode);

        String userId = (String) request.getSession().getAttribute("userId");
        UserDto userDto = userService.findById(userId);
        String phone = userDto.getMobile();

        String msgCode = "SMS_158493437";

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = sendSmsResponse(phone,verificationCode,msgCode);
            return Message.success();
        } catch (ClientException e){
            LOGGER.error(e.getMessage(),e.getCause());
            return Message.fail();
        }
    }

    @ResponseBody
    @PostMapping("/changePassword")
    public Message changePassword(@RequestBody PasswordRequestDto passwordRequestDto, HttpServletRequest request) {
        try {
            String msgCode = (String) request.getSession().getAttribute("msgCode");
            if (StringUtils.isNotEmpty(passwordRequestDto.getMsgCode()) && passwordRequestDto.getMsgCode().equals(msgCode)) {
                String userId = (String) request.getSession().getAttribute("userId");
                UserDto userDto = userMapper.findUserByID(userId);

                ByteSource credentialsSalt = ByteSource.Util.bytes(userDto.getId());//Use account ID as salt value
                Object result = new SimpleHash("MD5", passwordRequestDto.getPassword(), credentialsSalt, 1024);

                userDto.setPassword(result.toString());
                userService.save(userDto, userId);
                return Message.success();
            }else{
                return Message.fail();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            return Message.fail();
        }
    }

    public String handleUserRoleNull(UserDto userDto) {
        if (userDto.getUserRoleDtos() == null) {
            return "UserRole";
        } else return null;
    }

    public static SendSmsResponse sendSmsResponse(String phoneNumber, String code, String msgCode) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //"***"分别填写自己的AccessKey ID和Secret
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI7h0wtLOyd7Uz", "AHpSdBKM37LYL3v8oadgcCKyyUGNzZ");
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        //填写接收方的手机号码
        request.setPhoneNumbers(phoneNumber);
        //此处填写已申请的短信签名
        request.setSignName("sunment");
        //此处填写获得的短信模版CODE
        request.setTemplateCode(msgCode);
        //笔者的短信模版中有${code}, 因此此处对应填写验证码
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            System.out.println("请求成功`");
        }
        return sendSmsResponse;
    }
}
