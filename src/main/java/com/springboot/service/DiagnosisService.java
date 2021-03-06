package com.springboot.service;

import com.springboot.dto.DiagnosisDto;

import java.util.List;

public interface DiagnosisService {
    List<DiagnosisDto> findAll();

    DiagnosisDto findById(String id);

    String save(DiagnosisDto diagnosisDto, String userId);
}
