package com.springboot.controller;

import com.springboot.dto.AppointmentDto;
import com.springboot.dto.Message;
import com.springboot.service.AppointmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("appointment")
public class AppointmentController {
    private static final Logger LOGGER = Logger.getLogger(AppointmentController.class);

    @Autowired
    @Qualifier("appointmentServiceImpl")
    AppointmentService appointmentService;

    @ResponseBody
    @GetMapping("appointment")
    public Message findAll() {
        try {
            List<AppointmentDto> appointmentDtos = appointmentService.findAll();

            return Message.success().add("list",appointmentDtos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

}
