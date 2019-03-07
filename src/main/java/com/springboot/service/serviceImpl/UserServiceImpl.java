package com.springboot.service.serviceImpl;

import com.springboot.dto.UserDto;
import com.springboot.dto.UserRoleDto;
import com.springboot.entity.BasicInformation;
import com.springboot.entity.User;
import com.springboot.entity.UserRole;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private static final String DEFAULT_PASSWORD = "123456";

    private static final String DOCTOR_ROLE_ID = "ROL0002";
    
    @Autowired
    @Qualifier("userRepository")
    UserRepository userRepository;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtos.add(userDto);
        });
        return userDtos;
    }

    @Override
    public Map<String, Object> findAllWithPage(Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
//        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
        Page<User> userPages = userRepository.findAll(PageRequest.of(pageNumber,pageSize,sort));
        List<User> users = userPages.getContent();

        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtos.add(userDto);
        });

        Map<String, Object> map = new HashMap<>();
        map.put("totalPage",userPages.getTotalPages());
        map.put("totalElement",userPages.getTotalElements());
        map.put("list", userDtos);
        return map;
    }


    @Override
    public UserDto findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserDto userDto = mapper.map(userOptional.get(), UserDto.class);
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public String save(UserDto userDto) {
        try {
            List<UserRoleDto> userRoleDtos = userDto.getUserRoles();
            List<UserRole> temp = new ArrayList<>();
            userRoleDtos.forEach(userRoleDto -> {
                String id = userRoleDto.getId();
                UserRoleDto roleDto = userRoleService.findById(id);
                UserRole userRole = mapper.map(roleDto,UserRole.class);
                temp.add(userRole);
            });

            Long count = userRepository.countById(userDto.getId());
            if (count > 0) {
                Optional<User> userOptional = userRepository.findById(userDto.getId());

                User user = userOptional.get();
                user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setMobile(userDto.getMobile());
                user.setUserRoles(temp);
                this.getModifiedInfo(user.getBasicInformation(), "1", 1);

                userRepository.save(user);
                return user.getId();
            } else {
                User user = mapper.map(userDto, User.class);

                user.setBasicInformation(new BasicInformation());
                this.getModifiedInfo(user.getBasicInformation(), "1", 1);

                //Password encryption
                String hashAlgorithmName = "MD5";//Encryption
                Object credentials = DEFAULT_PASSWORD;//Unencrypted password
                Object salt = ByteSource.Util.bytes(userDto.getId());//salt
                int hashIterations = 1024;//Encrypted 1024 times
                Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);

                user.setPassword(result.toString());
                user.setUserRoles(temp);
                userRepository.save(user);
                return user.getId();
            }
        } catch (Exception ex) {
            LOGGER.error("delete fail",ex);
            throw ex;
        }
    }

    @Override
    public void delete(List<String> userIdList) {
        try {
            userIdList.forEach(userId -> userRepository.deleteById(userId));
        } catch (Exception ex) {
            LOGGER.error("delete fail",ex);
            throw ex;
        }
    }

    @Override
    public List<UserDto> findByDoctor() {

        List<User> users = userRepository.findAll();

        List<UserDto> userDtoList = new ArrayList<>();
        users.forEach(user -> {
            UserRole userRole = user.getUserRoles().get(0);
            if (DOCTOR_ROLE_ID.equals(userRole.getId())) {
                UserDto userDto = mapper.map(user, UserDto.class);
                userDtoList.add(userDto);
            }
        });

        return userDtoList;
    }

    private BasicInformation getModifiedInfo(BasicInformation basicInformation, String userID, Integer ClinicCode) {
        if (basicInformation.getCreateBy() != null) {
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
            basicInformation.setUpdateClinic(ClinicCode);
        }else {
            basicInformation.setCreateBy(userID);
            basicInformation.setCreateDtm(new Date());
            basicInformation.setCreateClinic(ClinicCode);
            basicInformation.setUpdateBy(userID);
            basicInformation.setUpdateDtm(new Date());
            basicInformation.setUpdateClinic(ClinicCode);
        }
        
        return basicInformation;
    }
}
