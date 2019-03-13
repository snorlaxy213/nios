package com.springboot;

import com.springboot.dto.UserDto;
import com.springboot.mapper.UserMapper;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@MapperScan("com.springboot.mapper")
public class NiosApplicationTests {

    private static final Logger LOGGER = Logger.getLogger(NiosApplicationTests.class);

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Test
    public void testMapper() {
        UserDto userDto = userMapper.findUserByID("USR0001");
        System.out.println(userDto);
    }
}
