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

import java.util.ArrayList;
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

    //Mybatis
    @Test
    public void testMybatis() {
        UserDto userDto = userMapper.findUserByID("USR0001");
        System.out.println(userDto);
    }

    @Test
    public void testPageHelper() {
        PageHelper.startPage(0, 2);
        List<UserDto> userDtos = userMapper.findAll();
        PageInfo<UserDto> userDtoPageInfo = new PageInfo<>(userDtos);

        LOGGER.info(userDtoPageInfo);
    }

    @Test
    public void testFindByExample() {
        User user = new User();
        user.setOffice("骨科");
        user.setName("admin");
        List<UserDto> userDtos = userMapper.findWithExample(user);
        LOGGER.info(userDtos);
    }

    //Jpa
    @Test
    public void testFindAll() {
        List<UserDto> all = userService.findAll(1,5);

        LOGGER.info(all);
    }

    @Test
    public void testSave() {
        UserDto userDto = userService.findById("USR0010");
        userDto.setCurrentNum(1);
        userService.save(userDto,"USR0001");
    }

    @Test
    public void testDelete() {
        List<String> userids = new ArrayList<>();
        userids.add("USR0010");
        userService.delete(userids);
    }
}
