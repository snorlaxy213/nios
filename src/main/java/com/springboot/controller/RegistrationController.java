package com.springboot.controller;

import com.springboot.dto.Message;
import com.springboot.dto.PatientDto;
import com.springboot.service.PatientService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("patientServiceImpl")
    PatientService patientService;

    @ResponseBody
    @GetMapping("/registration")
    public Message findAll() {
        try {
            List<PatientDto> patientDtos = patientService.findAll();
            return Message.success().add("list",patientDtos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/registration/{id}")
    public Message findById(@PathVariable(value = "id") String id) {
        try {
            PatientDto patientDto = patientService.findById(id);
            return Message.success().add("patient", patientDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @PostMapping("/registration")
    public Message save(@RequestBody PatientDto patientDto) {
        try {
            patientService.save(patientDto);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }
}
