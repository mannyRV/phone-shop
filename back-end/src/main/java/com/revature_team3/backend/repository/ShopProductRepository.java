package com.revature_team3.backend.repository;

import com.revature_team3.backend.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct,Integer> {
}
