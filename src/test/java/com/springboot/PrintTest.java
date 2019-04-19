package com.springboot;

import com.springboot.commons.PrintUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PrintTest {
    private static final Logger LOGGER = Logger.getLogger(PrintTest.class);

    @Test
    public void testPrint() {
        PrintUtil printUtil = new PrintUtil();
        printUtil.generatePdfByte();
    }
}
