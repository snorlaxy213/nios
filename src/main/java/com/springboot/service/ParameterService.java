package com.springboot.service;

import com.springboot.entity.Parameter;

public interface ParameterService {
    int getValue(String Parameter);

    void insertValue(Parameter parameter);
}
