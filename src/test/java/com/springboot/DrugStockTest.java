package com.springboot;

import com.springboot.dto.DiagnosisDto;
import com.springboot.dto.DrugProfileDto;
import com.springboot.dto.DrugStockDto;
import com.springboot.service.DiagnosisService;
import com.springboot.service.DrugProfileService;
import com.springboot.service.DrugStockService;
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
public class DrugStockTest {
    private static final Logger LOGGER = Logger.getLogger(DrugStockTest.class);

    @Autowired
    @Qualifier("drugStockServiceImpl")
    DrugStockService drugStockService;

    @Autowired
    @Qualifier("diagnosisServiceImpl")
    DiagnosisService diagnosisService;

    @Autowired
    @Qualifier("drugProfileServiceImpl")
    DrugProfileService drugProfileService;

    @Test
    public void saveTest() {
        DrugStockDto drugStockDto = new DrugStockDto();
        DiagnosisDto diagnosisDto = diagnosisService.findById("DIA0012");
        DrugProfileDto drugProfileDto = drugProfileService.findById("CCM0001");
        drugStockDto.setDiagnosisDto(diagnosisDto);
        drugStockDto.setDrugProfileDto(drugProfileDto);

        drugStockService.save(drugStockDto,"USR0001");
    }
}
