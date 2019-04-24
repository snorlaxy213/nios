package com.springboot.repository;

import com.springboot.entity.DrugProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("drugProfileRepository")
public interface DrugProfileRepository extends JpaRepository<DrugProfile,String> {
    Long countById(String id);

    DrugProfile findByName(String name);
}
