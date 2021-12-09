package com.revature_team3.backend.Repository;

import com.revature_team3.backend.Entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopProductRepository extends JpaRepository<ShopProduct,Integer> {
}
