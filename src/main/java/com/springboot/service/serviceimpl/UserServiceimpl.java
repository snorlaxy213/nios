package com.springboot.service.serviceimpl;

import com.springboot.dto.UserDto;
import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceimpl")
public class UserServiceimpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Override
    public User findByname(String username) throws Exception {
        List<User> users = userRepository.findAll();
        User temp = null;
        for(User user : users){
            if(username.equals(user.getName())){
                temp = user;
            }
        }
        return temp;
    }

    @Override
    public String save(UserDto userDto) throws Exception {
        try {
            if (validation(userDto)) {
                return "input error";
            }
            User user = mapper.map(userDto,User.class);
            userRepository.save(user);
            return "ewq";
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
}
