package com.springboot;

import com.springboot.entity.Parameter;
import com.springboot.service.ParameterService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ParameterTest {
    private static final Logger LOGGER = Logger.getLogger(ParameterTest.class);

    @Autowired
    @Qualifier("parameterServiceImpl")
    ParameterService parameterService;

    @Test
    public void insertValue() {
        Parameter parameter = new Parameter();
        parameter.setParameter("AppointmentSequence");
        parameter.setValue(0);

        parameterService.insertValue(parameter);
    }
}
