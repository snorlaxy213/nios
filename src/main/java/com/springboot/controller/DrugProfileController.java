package com.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.commons.PageUtils;
import com.springboot.dto.DrugProfileDto;
import com.springboot.dto.DrugRestockDto;
import com.springboot.dto.Message;
import com.springboot.service.DrugProfileService;
import com.springboot.service.DrugRestockService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    @Qualifier("drugRestockServiceImpl")
    DrugRestockService drugRestockService;

    @ResponseBody
    @GetMapping("/drugProfile")
    public Message findAll(@RequestParam(value = "pageNumber", defaultValue = "1")Integer pageNumber) {
        try {
            PageInfo pageInfo = drugProfileService.findAllByMybatis(pageNumber, PageUtils.PAGE_SIZE);

            return Message.success().add("pageInfo", pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("getAllDrugProfile")
    public Message findAllDrug() {
        try {
            List<DrugProfileDto> dtos = drugProfileService.findAll();

            return Message.success().add("list", dtos);
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
    public Message save(@RequestBody DrugProfileDto drugProfileDto, HttpServletRequest request) {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            drugProfileService.save(drugProfileDto, userId);
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

    @ResponseBody
    @PostMapping("/drugProfileUpload")
    public Message uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            drugProfileService.batchSave(file,userId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw e;
        }
        return Message.success();
    }

    @ResponseBody
    @PostMapping("/drugRestock")
    public Message restock(@RequestBody DrugRestockDto drugRestockDto, HttpServletRequest request) {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            drugRestockService.save(drugRestockDto, userId);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw e;
        }
    }
}
