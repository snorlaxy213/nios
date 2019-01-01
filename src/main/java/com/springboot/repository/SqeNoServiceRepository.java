package com.springboot.repository;


import com.springboot.entity.TableIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqeNoServiceRepository extends JpaRepository<TableIdentity,String> {

}
