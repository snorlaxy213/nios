package com.springboot.service;

import com.springboot.dto.DiagnosisDto;

import java.util.List;

public interface DiagnosisService {
    List<DiagnosisDto> findAll();

    DiagnosisDto findById(String id);

    void save(DiagnosisDto diagnosisDto);
}
