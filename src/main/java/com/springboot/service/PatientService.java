package com.springboot.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dto.PatientDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> findAll();

    PageInfo findAllByMybatis(Integer pageNumber, Integer pageSize);

    PatientDto findById(String id);

    void save(PatientDto patientDto, String userId);

    void delete(String id, String userId);
}
