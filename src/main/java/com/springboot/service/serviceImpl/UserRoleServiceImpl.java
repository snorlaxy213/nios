package com.springboot.service.serviceImpl;

import com.springboot.dto.UserRoleDto;
import com.springboot.dto.User_UserRole;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.UserRole;
import com.springboot.repository.UserRoleRepository;
import com.springboot.service.SqeNoService;
import com.springboot.service.UserRoleService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("userRoleServiceImpl")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger LOGGER = Logger.getLogger(UserRoleServiceImpl.class);

    private static final String TABLE_NAME = "USER_ROLE";

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("userRoleRepository")
    UserRoleRepository userRoleRepository;

    @Autowired
    @Qualifier("sqeNoServiceImpl")
    SqeNoService sqeNoService;

    @Override
    public UserRoleDto findUserRoleById(String id) {
        Optional<UserRole> optional = userRoleRepository.findById(id);

        UserRoleDto userRoleDto = null;
        if (optional.isPresent()) {
            userRoleDto = mapper.map(optional.get(), UserRoleDto.class);
        }
        return userRoleDto;
    }

    @Override
    public List<UserRoleDto> findAll() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        userRoles.forEach(userRole -> {
            UserRoleDto userRoleDto = mapper.map(userRole, UserRoleDto.class);
            userRoleDtos.add(userRoleDto);
        });

        return userRoleDtos;
    }

    @Override
    public UserRoleDto findById(String id) {
        Optional userRoleOptional = userRoleRepository.findById(id);
        UserRoleDto userRoleDto;
        if (userRoleOptional.isPresent()) {
            userRoleDto = mapper.map(userRoleOptional.get(), UserRoleDto.class);
            return userRoleDto;
        } else {
            return null;
        }
    }

    @Override
    public List<User_UserRole> findUser_Role(String userId) {
        List<Object[]> examples = userRoleRepository.findUserRole(userId);

        Iterator iterator = examples.iterator();
        List<User_UserRole> user_userRoles = new ArrayList<>();
        for (int i = 0; i < examples.size(); i++) {
            Object[] objects = (Object[])iterator.next();
            User_UserRole user_userRole = new User_UserRole();
            user_userRole.setUserId((String) objects[0]);
            user_userRole.setUserRoleId((String) objects[1]);
            user_userRoles.add(user_userRole);
        }

        return user_userRoles;
    }

    @Override
    public String save(UserRoleDto userRoleDto) {
        Long count = userRoleRepository.countById(userRoleDto.getId());

        if (count > 0) {
            Optional<UserRole> userRoleOptional = userRoleRepository.findById(userRoleDto.getId());

            userRoleOptional.ifPresent(userRole -> {
                userRole.setId(userRoleDto.getId());
                userRole.setName(userRoleDto.getName());
                userRole.setStatus(userRoleDto.getStatus());

                this.getModifiedInfo(userRole.getBasicInformation(), "1", 1);

                userRoleRepository.save(userRole);

            });
            return userRoleDto.getId();
        } else {
            String id = sqeNoService.getSeqNo(TABLE_NAME);

            UserRole userRole = mapper.map(userRoleDto, UserRole.class);
            userRole.setId(id);
            this.getModifiedInfo(userRole.getBasicInformation(), "1", 1);
            userRoleRepository.save(userRole);

            return userRoleDto.getId();
        }

    }

    private BasicInformation getModifiedInfo(BasicInformation aBscRwInf, String lUID, Integer lClinicCode) {

        if (aBscRwInf != null) {
            if (aBscRwInf.getCreateBy() == null) {
                aBscRwInf.setCreateBy(lUID);
                aBscRwInf.setCreateDtm(new Date());
                aBscRwInf.setCreateClinic(lClinicCode);
            } else {
                aBscRwInf.setUpdateBy(lUID);
                aBscRwInf.setUpdateDtm(new Date());
                aBscRwInf.setUpdateClinic(lClinicCode);
            }
        }

        return aBscRwInf;
    }
}
