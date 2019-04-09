package com.springboot;

import com.springboot.dto.AppointmentDto;
import com.springboot.dto.DiagnosisDto;
import com.springboot.dto.PatientDto;
import com.springboot.dto.UserDto;
import com.springboot.service.AppointmentService;
import com.springboot.service.DiagnosisService;
import com.springboot.service.PatientService;
import com.springboot.service.UserService;
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
public class DiagnosisTest {
    private static final Logger LOGGER = Logger.getLogger(DiagnosisTest.class);

    @Autowired
    @Qualifier("diagnosisServiceImpl")
    DiagnosisService diagnosisService;

    @Autowired
    @Qualifier("patientServiceImpl")
    PatientService patientService;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("appointmentServiceImpl")
    AppointmentService appointmentService;

    @Test
    public void findAllTest() {
        List<DiagnosisDto> diagnosisDtos = diagnosisService.findAll();

        LOGGER.info(diagnosisDtos);
    }

    @Test
    public void findByIdTest() {
        DiagnosisDto diagnosisDto = diagnosisService.findById("DIA0001");

        LOGGER.info(diagnosisDto);
    }

    @Test
    public void save() {
        DiagnosisDto diagnosisDto = new DiagnosisDto();

        diagnosisDto.setId("DIA0002");
        diagnosisDto.setDescription("test");

        UserDto userDto = userService.findById("USR0001");
        diagnosisDto.setUserDto(userDto);
        PatientDto patientDto = patientService.findById("PAT0001");
        diagnosisDto.setPatientDto(patientDto);
        AppointmentDto appointmentDto = appointmentService.findByID("APP0001");
        diagnosisDto.setAppointmentDto(appointmentDto);

        diagnosisService.save(diagnosisDto,"USR0001");
    }
}
