package com.springboot;

import com.springboot.dto.UserRoleDto;
import com.springboot.dto.User_UserRole;
import com.springboot.entity.BasicInformation;
import com.springboot.mapper.UserRoleMapper;
import com.springboot.service.UserRoleService;
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
public class UserRoleTest {

    private static final Logger LOGGER = Logger.getLogger(UserRoleTest.class);

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("userRoleMapper")
    UserRoleMapper userRoleMapper;

    @Test
    public void testUserRole() {
        List<User_UserRole> message = userRoleService.findUser_Role("USR0001");

        LOGGER.info(message);
    }

    @Test
    public void saveUserRole() {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setName("test");
        userRoleDto.setStatus("Y");
        userRoleDto.setBasicInformation(new BasicInformation());

        userRoleService.save(userRoleDto,"USR0001");
    }

    @Test
    public void findUserRoleMapper() {
        List<UserRoleDto> userRoleDtos = userRoleMapper.findUserRole();

        System.out.println(userRoleDtos);
    }


}
