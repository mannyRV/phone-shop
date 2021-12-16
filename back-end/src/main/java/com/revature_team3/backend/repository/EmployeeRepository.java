package com.revature_team3.backend.repository;

import com.revature_team3.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("from Employee e where e.email=:email")
    Employee findByEmail(String email);

}
