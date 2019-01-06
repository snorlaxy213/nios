package com.springboot;

import com.springboot.dto.Message;
import com.springboot.dto.UserRoleDto;
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

import java.util.List;

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
        Message users = userService.findAllWithPage(0,1);

    }

    @Test
    public void tsetUserRole() {
        List<UserRoleDto> userRoleDtos = userRoleService.findAll();

        LOGGER.info(userRoleDtos);
    }

}
