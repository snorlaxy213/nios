package com.springboot.service.serviceImpl;

import com.springboot.dto.AppointmentDto;
import com.springboot.entity.Appointment;
import com.springboot.repository.AppointmentRepository;
import com.springboot.service.AppointmentService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("appointmentServiceImpl")
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    @Qualifier("mapper")
    Mapper mapper;

    @Autowired
    @Qualifier("appointmentRepository")
    AppointmentRepository appointmentRepository;

    @Override

    public List<AppointmentDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();

        if (appointments.size() > 0) {
            List<AppointmentDto> appointmentDtos = new ArrayList<>();
            appointments.forEach(appointment -> {
                appointmentDtos.add(mapper.map(appointment, AppointmentDto.class));
            });
            return appointmentDtos;
        } else return null;
    }
}
