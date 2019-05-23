package com.springboot.controller;

import com.springboot.dto.AppointmentDto;
import com.springboot.dto.Message;
import com.springboot.mapper.AppointmentMapper;
import com.springboot.service.AppointmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("appointment")
public class AppointmentController {
    private static final Logger LOGGER = Logger.getLogger(AppointmentController.class);

    @Autowired
    @Qualifier("appointmentServiceImpl")
    AppointmentService appointmentService;

    @Autowired
    @Qualifier("appointmentMapper")
    AppointmentMapper appointmentMapper;

    @ResponseBody
    @GetMapping("/appointment")
    public Message findAll() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            List<AppointmentDto> appointmentDtos = appointmentMapper.findAll("Y");
            for (AppointmentDto appointmentDto : appointmentDtos) {
                appointmentDto.setAppointmentTime_str(sdf.format(appointmentDto.getAppointmentTime()));
            }

            return Message.success().add("list",appointmentDtos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/appointment/{id}")
    public Message findById(@PathVariable(value = "id") String id) {
        try {
            AppointmentDto appointmentDto = appointmentMapper.findById(id);
            return Message.success("success").add("appointment",appointmentDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/getCount")
    public Message getCount() {
        try {
            long count = appointmentService.getCount();
            return Message.success("success").add("count",count);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @PostMapping("/appointment")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Message save(@RequestBody @Valid AppointmentDto appointmentDto, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        try {
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                List<String> errorMessages = new ArrayList<>();
                for (ObjectError error : allErrors) {
                    String defaultMessage = error.getDefaultMessage();
                    errorMessages.add(defaultMessage);
                }

                return Message.validation(300, errorMessages);
            }
            String id = appointmentDto.getPatientDto().getId();
            Long byPatientKey = appointmentMapper.findByPatientKey(id);
            if (byPatientKey > 0) {
                return Message.fail("只能同时有一个预约");
            }
            Date appointmentTime = appointmentDto.getAppointmentTime();
            Calendar instance = Calendar.getInstance();
            instance.setTime(appointmentTime);
            int i = instance.get(10);
            instance.set(10, i - 8);
            String doctorId = appointmentDto.getUserDto().getId();
            HashMap<String,Object> map = new HashMap<>();
            map.put("userId", doctorId);

            map.put("time", instance.getTime());
            Long byTimeAndUserId = appointmentMapper.findByTimeAndUserId(map);
            if (byTimeAndUserId > 0) {
                return Message.fail("预约时间冲突");
            }

            appointmentDto.setAppointmentTime(instance.getTime());
            String userId = (String) request.getSession().getAttribute("userId");
            appointmentService.save(appointmentDto, userId);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }





}
