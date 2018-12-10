package com.springboot.service;

import com.springboot.entity.User;


public interface UserService {
    User findByname(String username);
}
