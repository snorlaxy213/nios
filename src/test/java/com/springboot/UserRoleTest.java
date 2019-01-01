package com.springboot;

import com.springboot.dto.UserRoleDto;
import com.springboot.entity.BasicInfomation;
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

    @Test
    public void tsetUserRole() {
        List<UserRoleDto> userRoleDtos = userRoleService.findUserRole();

        LOGGER.info(userRoleDtos);
    }

    @Test
    public void saveUserRole() {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setDescription("test");
        userRoleDto.setName("test");
        userRoleDto.setStatus("Y");
        userRoleDto.setBasicInfomation(new BasicInfomation());

        userRoleService.save(userRoleDto);
    }
}
