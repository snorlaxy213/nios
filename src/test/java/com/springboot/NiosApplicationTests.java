package com.springboot;

import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class NiosApplicationTests {

    private static final Logger LOGGER = Logger.getLogger(NiosApplicationTests.class);

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @Test
    public void contextLoads() {
        Map<String,Object> users = userService.findAllWithPage(0,1);
        System.out.println(users);
    }
}
