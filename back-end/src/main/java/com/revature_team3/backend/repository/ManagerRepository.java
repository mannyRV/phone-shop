package com.revature_team3.backend.repository;

import com.revature_team3.backend.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    @Query("from Manager m where m.email=:email")
    Manager findByEmail(String email);
}
