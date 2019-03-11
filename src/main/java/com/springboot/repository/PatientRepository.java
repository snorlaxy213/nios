package com.springboot.repository;

import com.springboot.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("patientRepository")
public interface PatientRepository extends JpaRepository<Patient,String> {
    Long countById(String id);
}
