package com.springboot;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dto.UserDto;
import com.springboot.entity.User;
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

import java.util.List;

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

    @Test
    public void testPagehelper() {
        PageHelper.startPage(0, 2);
        List<UserDto> userDtos = userMapper.findAll();
        PageInfo<UserDto> userDtoPageInfo = new PageInfo<>(userDtos);

        LOGGER.info(userDtoPageInfo);
    }

    @Test
    public void testFindByDoctorAndOffice() {
//        List<UserDto> userDtos = userService.findByDoctorAndOffice("骨科", "admin");
        User user = new User();
        user.setOffice("骨科");
//        user.setName("admin");
        List<UserDto> userDtos = userMapper.findWithExample(user);
        LOGGER.info(userDtos);
    }
}
