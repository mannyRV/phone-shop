package com.revature_team3.backend.Repository;

import com.revature_team3.backend.Entity.OwnedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnedProductRepository extends JpaRepository<OwnedProduct,Integer> {

    @Query("from OwnedProduct o where o.customer.id:=customer_id")
    public List<OwnedProduct> findAllByUserId(int customer_id);

}
