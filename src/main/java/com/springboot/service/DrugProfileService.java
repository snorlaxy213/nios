package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dto.DrugProfileDto;

import java.util.List;

public interface DrugProfileService {
    List<DrugProfileDto> findAll();

    PageInfo findAllByMybatis(Integer pageNumber, Integer pageSize);

    DrugProfileDto findById(String id);

    void save(DrugProfileDto drugProfileDto, String userId);

    void delete(List<String> drugIdList);
}
