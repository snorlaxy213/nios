package com.springboot;

import com.springboot.commons.CommonTableUtils;
import com.springboot.dto.DrugProfileDto;
import com.springboot.service.DrugProfileService;
import com.springboot.service.SqeNoService;
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
public class DrugProfileTest {
    private static final Logger LOGGER = Logger.getLogger(DrugProfileTest.class);

    @Autowired
    @Qualifier("drugProfileServiceImpl")
    DrugProfileService drugProfileService;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Test
    public void save() {
        DrugProfileDto drugProfileDto = new DrugProfileDto();
        drugProfileDto.setId(sqeNoService.getSeqNo(CommonTableUtils.DRUG));
        drugProfileDto.setName("八仙草");
        drugProfileDto.setType("配方颗粒");
        drugProfileDto.setDescription("Test");
        drugProfileDto.setUnit("Gra");
        drugProfileDto.setStatus("A");

        drugProfileService.save(drugProfileDto);
    }

    @Test
    public void findAll() {
        List<DrugProfileDto> profileDtos = drugProfileService.findAll();

        LOGGER.info(profileDtos.get(0));
    }

    @Test
    public void findById() {
        DrugProfileDto drugProfileDto = drugProfileService.findById("CCM0001");

        LOGGER.info(drugProfileDto);
    }
}
