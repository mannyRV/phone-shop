package com.revature_team3.backend.repository;

import com.revature_team3.backend.entity.OwnedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnedProductRepository extends JpaRepository<OwnedProduct,Integer> {

    @Query("from OwnedProduct o where o.customer.id=:customer_id")
    List<OwnedProduct> findAllByUserId(int customer_id);

}
