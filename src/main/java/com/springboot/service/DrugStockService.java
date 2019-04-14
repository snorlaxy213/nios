package com.springboot.service;

import com.springboot.dto.DrugStockDto;

public interface DrugStockService {
    void save(DrugStockDto drugStockDto, String userId);
}
