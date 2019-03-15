package com.springboot.repository;

import com.springboot.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("patientRepository")
public interface PatientRepository extends JpaRepository<Patient,String> {
    Long countById(String id);

    List<Patient> findPatientByStatus(String status);
}
