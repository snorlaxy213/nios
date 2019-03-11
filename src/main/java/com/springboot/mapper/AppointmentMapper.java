package com.springboot.mapper;

import com.springboot.dto.AppointmentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("appointmentMapper")
public interface AppointmentMapper {
    List<AppointmentDto> findAll();

    AppointmentDto findById(@Param("Id") String Id);
}
