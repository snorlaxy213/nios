package com.springboot.service.serviceImpl;

import com.springboot.dto.UserDto;
import com.springboot.entity.BasicInfomation;
import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = mapper.map(user,UserDto.class);
            userDtos.add(userDto);
        });

        return userDtos;
    }

    @Override
    public Page<User> findAllWithPage(Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);

        Page<User> users = userRepository.findAll(pageable);

        return users;
    }


    @Override
    public User findById(String id) {
        List<User> users = userRepository.findAll();

        User temp = null;
        for(User user : users){
            if(id.equals(user.getId())){
                temp = user;
            }
        }
        return temp;
    }

    @Override
    public String save(UserDto userDto) {
        try {
            if (validation(userDto)) {
                return "input error";
            }
            User user = mapper.map(userDto,User.class);
            this.getModifiedInfo(user.getBasicInfomation(), "1", 1);
            user.setPassword("123456");
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            throw e;
        }
    }

    boolean validation(UserDto userDto){
        if (userDto.getId() == null || userDto.getEffective() == null || userDto.getEmail() == null
                || userDto.getName() == null) {
            return true;
        } else {
            return false;
        }
    }

    private BasicInfomation getModifiedInfo(BasicInfomation aBscRwInf, String lUID, Integer lClinicCode) {

        if(aBscRwInf!=null){
            if(aBscRwInf.getCreateBy() == null){
                aBscRwInf.setCreateBy(lUID);
                aBscRwInf.setCreateDtm(new Date());
                aBscRwInf.setCreateClinic(lClinicCode);
                aBscRwInf.setUpdateBy(lUID);
                aBscRwInf.setUpdateDtm(new Date());
                aBscRwInf.setUpdateClinic(lClinicCode);
            }else{
                aBscRwInf.setUpdateBy(lUID);
                aBscRwInf.setUpdateDtm(new Date());
                aBscRwInf.setUpdateClinic(lClinicCode);
            }
        }

        return aBscRwInf;
    }
}
