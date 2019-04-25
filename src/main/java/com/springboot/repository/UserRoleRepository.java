package com.springboot.repository;

import com.springboot.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    Long countById(String id);

    List<UserRole> findByStatus(String status);

    @Query(value = "select * from user_user_role u where u.user_id = :userId", nativeQuery = true)
    List<Object[]> findUserRole(@Param("userId") String userId);

}
