package com.revature_team3.backend.repository;

import com.revature_team3.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("from Order o where o.customer.id=customer_id")
    List<Order> findAllByUserId(int customer_id);
}
