package com.springboot.repository;

import com.springboot.entity.DrugStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("drugStockRepository")
public interface DrugStockRepository extends JpaRepository<DrugStock,String> {

}
