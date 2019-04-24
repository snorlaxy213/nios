package com.springboot.controller;

import com.springboot.dto.DispensingDto;
import com.springboot.dto.Message;
import com.springboot.service.DispensingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dispensing")
public class DispensingController {

    private static final Logger LOGGER = Logger.getLogger(DispensingController.class);

    @Autowired
    @Qualifier("dispensingServiceImpl")
    private DispensingService dispensingService;

    @ResponseBody
    @GetMapping("dispensing")
    public Message findByDiagnosisID(@RequestParam(value = "diagnosisID") String diagnosisID) throws Exception {
        try {
            DispensingDto dispensingDto = dispensingService.findByDiagnosisID(diagnosisID);
            return Message.success().add("dispensing", dispensingDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

    @ResponseBody
    @PostMapping("dispensing")
    public Message save(@RequestParam(value = "diagnosisID") String diagnosisID, HttpServletRequest request) throws Exception {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            String message = dispensingService.dispensing(diagnosisID,userId);
            return Message.success().add("message", message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }
}
