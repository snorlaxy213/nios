package com.springboot.service.serviceimpl;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByname(String username) {
        List<User> users = userRepository.findAll();
        User temp = null;
        for(User user : users){
            if(username.equals(user.getName())){
                temp = user;
            }
        }
        return temp;
    }
}
