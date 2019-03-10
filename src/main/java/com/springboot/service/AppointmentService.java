package com.springboot.service;

import com.springboot.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> findAll();
}
