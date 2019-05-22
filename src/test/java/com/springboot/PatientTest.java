package com.springboot;

import com.github.pagehelper.PageInfo;
import com.springboot.commons.Constants;
import com.springboot.dto.PatientDto;
import com.springboot.service.PatientService;
import com.springboot.service.SqeNoService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
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
public class PatientTest {
    private static final Logger LOGGER = Logger.getLogger(PatientTest.class);

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("patientServiceImpl")
    PatientService patientService;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Test
    public void findAllTest() {
        List<PatientDto> patientDtos = patientService.findAll();

        LOGGER.info(patientDtos);
    }

    @Test
    public void findByIdTest() {
        PatientDto patientDto = patientService.findById("PAT0001");

        LOGGER.info(patientDto);
    }

    @Test
    public void saveTest() {

        PatientDto patientDto = new PatientDto();
        patientDto.setId(sqeNoService.getSeqNo(Constants.PATIENT));
        patientDto.setName("chan");
        patientDto.setAge(20L);
        patientDto.setEmail("1658895307@qq.com");
        patientDto.setGender("M");
        patientDto.setMobile("1658895307");

        patientService.save(patientDto,"USR0001");

        LOGGER.info(patientDto);
    }

    @Test
    public void findAllMybatisTest() {

        PageInfo pageInfo = patientService.findAllByMybatis(0, 5);

        LOGGER.info(pageInfo);
    }

}
