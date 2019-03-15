package com.springboot.service;

import com.springboot.dto.PatientDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> findAll();

    PatientDto findById(String id);

    void save(PatientDto patientDto);

    void delete(String id);
}
