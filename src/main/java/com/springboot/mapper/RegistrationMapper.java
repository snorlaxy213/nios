package com.springboot.mapper;

import com.springboot.dto.PatientDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("registrationMapper")
public interface RegistrationMapper {
    List<PatientDto> findAll();
}
