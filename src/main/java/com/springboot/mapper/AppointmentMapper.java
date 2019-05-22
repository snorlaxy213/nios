package com.springboot.mapper;

import com.springboot.dto.AppointmentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("appointmentMapper")
public interface AppointmentMapper {
    List<AppointmentDto> findAll(@Param("status") String status);

    AppointmentDto findById(@Param("Id") String Id);

    Long findByPatientKey(@Param("patientKey") String patientKey);

    Long findByTimeAndUserId(@Param("params") Map<String, Object> map);
}
