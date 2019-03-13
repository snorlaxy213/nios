package com.springboot;

import com.springboot.dto.AppointmentDto;
import com.springboot.mapper.AppointmentMapper;
import com.springboot.service.AppointmentService;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
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
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("appointmentMapper")
    private AppointmentMapper appointmentMapper;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Test
    public void findAll() {
        List<AppointmentDto> appointmentDtos = appointmentService.findAll();

        LOGGER.info(appointmentDtos);
    }

    @Test
    public void findByID() {
//        AppointmentDto appointmentDto = appointmentService.findByID("APP0001");

        AppointmentDto appointmentDto = appointmentMapper.findById("APP0001");
        LOGGER.info(appointmentDto);
    }

    @Test
    public void save() throws Exception {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId("APP002");
        appointmentDto.setAppointmentTime(new Date());
        appointmentDto.setDescription("test");
        appointmentDto.setDuration("30");
        appointmentDto.setUserDto(userService.findById("USR0001"));

        appointmentService.save(appointmentDto);
        LOGGER.info(appointmentDto);
    }
}
