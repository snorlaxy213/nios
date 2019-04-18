package com.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("dispensingMapper")
public interface DispensingMapper {
    List<Map<String, Object>> findByDiagnosisID(@Param("diagnosisID") String diagnosisID);
}
