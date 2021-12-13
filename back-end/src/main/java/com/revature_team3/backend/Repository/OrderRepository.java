package com.revature_team3.backend.Repository;

import com.revature_team3.backend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("from Order o where o.customer.id:=customer_id")
    public List<Order> findAllByUserId(int customer_id);
}
