package com.revature_team3.backend.repository;

import com.revature_team3.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("from Customer c where c.email=email")
    Customer findByEmail(String email);

}
