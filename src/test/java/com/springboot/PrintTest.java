package com.springboot;

import com.springboot.commons.PrintUtil;
import com.springboot.dto.DispensingDto;
import com.springboot.service.DispensingService;
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
public class PrintTest {
    private static final Logger LOGGER = Logger.getLogger(PrintTest.class);

    @Autowired
    @Qualifier("dispensingServiceImpl")
    private DispensingService dispensingService;

    @Test
    public void testPrint() {
        try {
            PrintUtil printUtil = new PrintUtil();
            DispensingDto dispensingDto = dispensingService.findByDiagnosisID("DIA0014");
            String pdfByte = printUtil.generatePdfByte(dispensingDto);
            LOGGER.info("base64:"+pdfByte);
        } catch (Exception e) {
            LOGGER.error("ERROR");
        }
    }
}
