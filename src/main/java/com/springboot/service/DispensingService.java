package com.springboot.service;

import com.springboot.dto.DispensingDto;

public interface DispensingService {
    DispensingDto findByDiagnosisID(String diagnosisID) throws Exception;

    String dispensing(String diagnosisID, String userId) throws Exception;
}
