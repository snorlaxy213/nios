package com.springboot.repository;

import com.springboot.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment,String> {
    Long countById(String id);

    List findAllByStatus(String status);
}
