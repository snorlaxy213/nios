package com.springboot.controller;

import com.springboot.dto.DrugProfileDto;
import com.springboot.dto.Message;
import com.springboot.service.DrugProfileService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/drugProfile")
public class DrugProfileController {
    private static final Logger LOGGER = Logger.getLogger(DrugProfileController.class);

    @Autowired
    @Qualifier("drugProfileServiceImpl")
    DrugProfileService drugProfileService;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @ResponseBody
    @GetMapping("/drugProfile")
    public Message findAll() {
        try {
            List<DrugProfileDto> drugProfileDtos = drugProfileService.findAll();

            return Message.success().add("list", drugProfileDtos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }

    }

    @ResponseBody
    @GetMapping("/drugProfile/{id}")
    public Message findById(@PathVariable(value = "id") String id) {
        try {
            DrugProfileDto drugProfileDto = drugProfileService.findById(id);
            return Message.success().add("drug", drugProfileDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }

    }

    @ResponseBody
    @PostMapping("/drugProfile")
    public Message save(@RequestBody DrugProfileDto drugProfileDto) {
        try {
            drugProfileService.save(drugProfileDto);

            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @DeleteMapping("/drugProfile/{drugIds}")
    public Message delete(@PathVariable(value = "drugIds") String drugIds) {
        try {
            List<String> userList = Arrays.asList(drugIds.split("-"));
            drugProfileService.delete(userList);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

}