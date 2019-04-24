package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dto.DrugProfileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DrugProfileService {
    List<DrugProfileDto> findAll();

    PageInfo findAllByMybatis(Integer pageNumber, Integer pageSize);

    DrugProfileDto findById(String id);

    DrugProfileDto findByNAME(String name);

    void save(DrugProfileDto drugProfileDto, String userId);

    void save(DrugProfileDto drugProfileDto, String userId, boolean batchSave);

    void delete(List<String> drugIdList);

    void batchSave(MultipartFile file,String userId);
}
