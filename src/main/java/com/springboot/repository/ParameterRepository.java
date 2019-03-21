package com.springboot.repository;

import com.springboot.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("parameterRepository")
public interface ParameterRepository extends JpaRepository<Parameter,String> {
    Parameter findByParameter(String parameter);
}
