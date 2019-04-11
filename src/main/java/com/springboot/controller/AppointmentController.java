package com.springboot.controller;

import com.springboot.dto.AppointmentDto;
import com.springboot.dto.Message;
import com.springboot.mapper.AppointmentMapper;
import com.springboot.service.AppointmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
            List<AppointmentDto> appointmentDtos = appointmentMapper.findAll("Y");

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
    @PostMapping("/appointment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Message save(@RequestBody AppointmentDto appointmentDto, HttpServletRequest request) throws Exception {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            appointmentService.save(appointmentDto, userId);
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }





}
