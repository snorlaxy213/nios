package com.springboot.service;

import com.springboot.dto.DrugRestockDto;
import com.springboot.exception.GlobalException;


public interface DrugRestockService {
    void save(DrugRestockDto restockDto,String userID) throws GlobalException;
}
