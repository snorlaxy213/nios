package com.springboot.controller;

import com.springboot.dto.DiagnosisDto;
import com.springboot.dto.Message;
import com.springboot.service.DiagnosisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

    private static final Logger LOGGER = Logger.getLogger(DiagnosisController.class);

    @Autowired
    @Qualifier("diagnosisServiceImpl")
    DiagnosisService diagnosisService;

    @ResponseBody
    @GetMapping("/diagnosis")
    public Message findAll() {
        try {
            List<DiagnosisDto> diagnosisDtos = diagnosisService.findAll();
            return Message.success("success").add("list",diagnosisDtos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/diagnosis/{id}")
    public Message findById(@PathVariable(value = "id") String id) {
        try {
            DiagnosisDto diagnosisDto = diagnosisService.findById(id);
            return Message.success("success").add("diagnosis",diagnosisDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @PostMapping("/diagnosis")
    public Message save(@RequestBody DiagnosisDto diagnosisDto, HttpServletRequest request) {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            diagnosisService.save(diagnosisDto, userId);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }
}
