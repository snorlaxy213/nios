package com.springboot.repository;

import com.springboot.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("diagnosisRepository")
public interface DiagnosisRepository extends JpaRepository<Diagnosis,String> {
    Long countById(String id);
}
