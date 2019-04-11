package com.springboot.repository;

import com.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,String> {

    Long countById(String id);

    List<User> findByOfficeOrName(String office, String name);
}
