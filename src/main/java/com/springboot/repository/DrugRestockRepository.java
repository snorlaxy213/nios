package com.springboot.repository;

import com.springboot.entity.DrugRestock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("drugRestockRepository")
public interface DrugRestockRepository extends JpaRepository<DrugRestock,String> {

}
