package com.springboot.service;

import com.springboot.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> findAll();

    AppointmentDto findByID(String Id);

    void save(AppointmentDto appointmentDto, String userId) throws Exception;

    void delete(String Id);

    void deleteSchedule();

    long getCount();
}
