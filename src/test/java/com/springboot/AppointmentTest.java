package com.springboot;

import com.springboot.dto.AppointmentDto;
import com.springboot.mapper.AppointmentMapper;
import com.springboot.service.AppointmentService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppointmentTest {
    private static final Logger LOGGER = Logger.getLogger(AppointmentTest.class);

    @Autowired
    @Qualifier("appointmentServiceImpl")
    AppointmentService appointmentService;

    @Autowired
    @Qualifier("appointmentMapper")
    private AppointmentMapper appointmentMapper;

    @Test
    public void findAll() {
        List<AppointmentDto> appointmentDtos = appointmentService.findAll();

        LOGGER.info(appointmentDtos);
    }
}
