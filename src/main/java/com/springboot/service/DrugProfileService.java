package com.springboot.service;

import com.springboot.dto.DrugProfileDto;

import java.util.List;

public interface DrugProfileService {
    List<DrugProfileDto> findAll();

    DrugProfileDto findById(String id);

    void save(DrugProfileDto drugProfileDto);

    void delete(List<String> drugIdList);
}
