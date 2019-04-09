package com.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.commons.PageUtils;
import com.springboot.dto.Message;
import com.springboot.dto.PatientDto;
import com.springboot.service.PatientService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Message findAll(@RequestParam(value = "pageNumber", defaultValue = "1")Integer pageNumber) {
        try {
//            List<PatientDto> patientDtos = patientService.findAll();
            PageInfo pageInfo = patientService.findAllByMybatis(pageNumber, PageUtils.PAGE_SIZE);
            return Message.success().add("pageInfo",pageInfo);
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
    public Message save(@RequestBody PatientDto patientDto, HttpServletRequest request) {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            patientService.save(patientDto,userId);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @DeleteMapping("/registration/{id}")
    public Message delete(@PathVariable(value = "id") String id, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("userId");
        patientService.delete(id, userId);

        return Message.success();
    }
}
